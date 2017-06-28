/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

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
 Semaphore semaforoFin;
 LinkedList<Hincha> hinchasAProcesar;

 
 public CentroOperaciones(Semaphore semaforoFin){
     this.semaforoOperaciones = new Semaphore(0);
     this.hinchasAProcesar = new LinkedList<Hincha>();
    this.semaforoFin = semaforoFin;
            }
 
 public void agregarHinchaAProcesar(Hincha nuevoHincha){
     if(nuevoHincha!=null){
        this.hinchasAProcesar.addLast(nuevoHincha);
        this.semaforoOperaciones.release();
     }
 }
 
 @Override
 public void run(){
         while(true){
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
}
