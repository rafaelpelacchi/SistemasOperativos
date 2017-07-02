/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemasOperativos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CentroOperaciones extends Thread {
 public Semaphore semaforoOperaciones;
 Semaphore semaforoHabilitarInsercion;
 private boolean terminoAmsterdam;
 private boolean terminoColombes;
 private boolean terminoOlimpica;
 private boolean terminoAmerica;
 LinkedList<Hincha> hinchasAProcesar;

 
 public CentroOperaciones(){
    this.semaforoOperaciones = new Semaphore(0);
    this.hinchasAProcesar = new LinkedList<Hincha>();
    this.semaforoHabilitarInsercion = new Semaphore(1);
    this.terminoAmsterdam = false;
    this.terminoColombes = false;
    this.terminoAmerica = false;
    this.terminoOlimpica = false;
            }
 
 public void agregarHinchaAProcesar(Hincha nuevoHincha){
     if(nuevoHincha!=null){
         try {
             this.semaforoHabilitarInsercion.acquire();
             this.hinchasAProcesar.addLast(nuevoHincha);
             this.semaforoHabilitarInsercion.release();
             this.semaforoOperaciones.release();
         } catch (InterruptedException ex) {
             Logger.getLogger(CentroOperaciones.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
 }

    public void setTerminoAmsterdam(boolean terminoAmsterdam) {
        this.terminoAmsterdam = terminoAmsterdam;
    }

    public void setTerminoColombes(boolean terminoColombes) {
        this.terminoColombes = terminoColombes;
    }

    public void setTerminoOlimpica(boolean terminoOlimpica) {
        this.terminoOlimpica = terminoOlimpica;
    }

    public void setTerminoAmerica(boolean terminoAmerica) {
        this.terminoAmerica = terminoAmerica;
    }
 
 @Override
 public void run(){
         while(hayMasHinchas()){
            try{
            this.semaforoOperaciones.acquire();
             Hincha hinchaAux = this.hinchasAProcesar.getFirst();
             hinchaAux.setLeido(true);
             this.hinchasAProcesar.removeFirst();
            } catch(InterruptedException ex){
                ex.printStackTrace();
                          System.out.println("Error");
            }
         }          
 }
 
     public boolean hayMasHinchas(){ 
        return !(terminoAmsterdam && terminoColombes && terminoOlimpica && terminoAmerica );
    }
}
