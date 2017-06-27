/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.concurrent.Semaphore;

/**
 * El reloj es la clase encargada de sincronizar tanto mis hilos que procesan himagenes como mis tribunas
 * el semaforo del reloj comienza habilitado, una vez que consume el semaforo se quedara esperando
 * a que todas tribunas dejen entrar a un hincha por funcionario. Esto representará un segundo
 */
public class Reloj extends Thread{
    private Semaphore semaforoFin;
    private Tiempo tiempoActual;
    private ControlTribuna controlAmsterdam;
    private ControlTribuna controlColombes;
    private ControlTribuna controlOlimpica;
    private ControlTribuna controlAmerica;
    
    public Reloj(ControlTribuna controlAmsterdam, ControlTribuna controlColombes, ControlTribuna controlOlimpica, 
            ControlTribuna controlAmerica, Tiempo tiempoActual, Semaphore semaforoFin){
        this.controlAmsterdam = controlAmsterdam;
        this.controlColombes = controlColombes;
        this.controlOlimpica = controlOlimpica;
        this.controlAmerica = controlAmerica;
        this.semaforoFin = semaforoFin;
        this.tiempoActual = tiempoActual;
    }
        
    /*
    * El método run comienza el reloj y solo para cuando todas las tribunas hayan procesado a sus hinchas
    */
    @Override
    public void run(){
         while(hayMasHinchas()){
             try{ 
                tiempoActual.aumentarTiempo();
                if(!controlAmsterdam.getTermino()){controlAmsterdam.getSemaphoreReloj().acquire();} 
                if(!controlColombes.getTermino()){controlColombes.getSemaphoreReloj().acquire();}
                if(!controlOlimpica.getTermino()){controlOlimpica.getSemaphoreReloj().acquire();}
                if(!controlAmerica.getTermino()){controlAmerica.getSemaphoreReloj().acquire();} 
                
            
                if(!controlAmsterdam.getTermino()){controlAmsterdam.getSemaphore().release();} 
                if(!controlColombes.getTermino()){controlColombes.getSemaphore().release();}
                if(!controlOlimpica.getTermino()){controlOlimpica.getSemaphore().release();}
                if(!controlAmerica.getTermino()){controlAmerica.getSemaphore().release();}  
             }
             catch(InterruptedException ex){
                ex.printStackTrace();
            }
         }
         this.semaforoFin.release();
    }
    
    public boolean hayMasHinchas(){ 
        boolean terminoAmsterdam = controlAmsterdam.getTermino();
        boolean terminoColombes = controlColombes.getTermino();
        boolean terminoOlimpica = controlOlimpica.getTermino();
        boolean terminoAmerica = controlAmerica.getTermino();
        return !(terminoAmsterdam && terminoColombes && terminoOlimpica && terminoAmerica );
    }
    
}
