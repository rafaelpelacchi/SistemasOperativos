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
    private Semaphore semaforoReloj;
    private ControlTribuna controlAmsterdam;
    private ControlTribuna controlColombes;
    private ControlTribuna controlOlimpica;
    private ControlTribuna controlAmerica;
    
    public Reloj(Semaphore semaforoAmsterdam, Semaphore semaforoReloj, ControlTribuna controlAmsterdam
                , ControlTribuna controlColombes, ControlTribuna controlOlimpica, ControlTribuna controlAmerica){
        this.controlAmsterdam = controlAmsterdam;
        this.controlColombes = controlColombes;
        this.controlOlimpica = controlOlimpica;
        this.controlAmerica = controlAmerica;
        this.semaforoReloj = semaforoReloj;
    }
        
    /*
    * El método run comienza el reloj y solo para cuando todas las tribunas hayan procesado a sus hinchas
    */
    @Override
    public void run(){
         while(hayMasHinchas()){
             try{ 
                 
                if(!controlAmsterdam.getTermino()){System.out.println("Adquiero Amsterdam"); controlAmsterdam.getSemaphoreReloj().acquire(); System.out.println("Quedo Adquirido Amsterdam");} 
                if(!controlColombes.getTermino()){System.out.println("Adquiero Colombes"); controlColombes.getSemaphoreReloj().acquire(); System.out.println("Quedo Adquirido Colombes");}
                if(!controlOlimpica.getTermino()){System.out.println("Adquiero Olimpica"); controlOlimpica.getSemaphoreReloj().acquire(); System.out.println("Quedo Adquirido Olimpica");}
                if(!controlAmerica.getTermino()){System.out.println("Adquiero America"); controlAmerica.getSemaphoreReloj().acquire(); System.out.println("Quedo Adquirido America");} 
                
            
                if(!controlAmsterdam.getTermino()){System.out.println("Voy a Liberar Amsterdam"); controlAmsterdam.getSemaphore().release(); System.out.println("Libere Amsterdam");} 
                if(!controlColombes.getTermino()){System.out.println("Voy a Liberar Colombes");  controlColombes.getSemaphore().release();System.out.println("Libere Colombes");}
                if(!controlOlimpica.getTermino()){ System.out.println("Voy a Liberar Olimpica"); controlOlimpica.getSemaphore().release();System.out.println("Libere Olimpica");}
                if(!controlAmerica.getTermino()){ System.out.println("Voy a Liberar America");  controlAmerica.getSemaphore().release();System.out.println("Libere America");}  
             }
             catch(InterruptedException ex){
                ex.printStackTrace();
            }
         }
         System.out.println("Se termino!");
    }
    
    public boolean hayMasHinchas(){ 
        boolean terminoAmsterdam = controlAmsterdam.getTermino();
        boolean terminoColombes = controlColombes.getTermino();
        boolean terminoOlimpica = controlOlimpica.getTermino();
        boolean terminoAmerica = controlAmerica.getTermino();
        return !(terminoAmsterdam && terminoColombes && terminoOlimpica && terminoAmerica );
    }
    
}
