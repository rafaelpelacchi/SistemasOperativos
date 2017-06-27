/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Estadio extends Thread {
   private String nombre;
   private Tribuna amsterdam;
   private Tribuna colombes;
   private Tribuna olimpica;
   private Tribuna america;
   private Reloj miReloj;
   private Semaphore semaforoImprimirReporte;
   private Salida salidaDelPrograma;
   
   public Estadio(String nombre,Tribuna amsterdam,Tribuna colombes,Tribuna olimpica,Tribuna america, 
           Reloj miReloj,Semaphore semaforoImprimirReporte, Salida salidaDelPrograma){
       this.nombre = nombre;
       this.amsterdam = amsterdam;
       this.colombes = colombes;
       this.olimpica = olimpica;
       this.america = america;
       this.miReloj = miReloj;
       this.semaforoImprimirReporte=semaforoImprimirReporte;
       this.salidaDelPrograma=salidaDelPrograma;
   }
   
   public void imprimirReporte(){
       ManejadorArchivosGenerico.escribirArchivo("src/prueba/HinchasEntraron.txt", salidaDelPrograma.retornarHinchasEntraror());
       ManejadorArchivosGenerico.escribirArchivo("src/prueba/HinchasEntraron.txt", salidaDelPrograma.retornarHinchasNoEntraror());
   }
   
   public void hacerEntrarGente(){
       
       try{
            this.semaforoImprimirReporte.acquire();
            this.miReloj.start();
            this.amsterdam.start();
            this.colombes.start();
            this.olimpica.start();
            this.america.start();
            this.semaforoImprimirReporte.acquire();
            imprimirReporte();
            
              } catch(InterruptedException ex){
                      ex.printStackTrace();
                       System.out.println("Error");
            }
            
   }
}
