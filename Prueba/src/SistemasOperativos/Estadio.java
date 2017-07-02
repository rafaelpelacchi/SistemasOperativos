/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemasOperativos;

import static java.time.Clock.*;
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
   private Semaphore semaforoFin;
   private Salida salidaDelPrograma;
   private CentroOperaciones centroDeOperaciones;
   
   public Estadio(String nombre,Tribuna amsterdam,Tribuna colombes,Tribuna olimpica,Tribuna america, 
           Reloj miReloj,Semaphore semaforoFin, Salida salidaDelPrograma,CentroOperaciones centroDeOperaciones){
       this.nombre = nombre;
       this.amsterdam = amsterdam;
       this.colombes = colombes;
       this.olimpica = olimpica;
       this.america = america;
       this.miReloj = miReloj;
       this.semaforoFin=semaforoFin;
       this.salidaDelPrograma=salidaDelPrograma;
       this.centroDeOperaciones = centroDeOperaciones;
   }
   
   public void imprimirReporte(){
       ManejadorArchivosGenerico.escribirArchivo("src/SistemasOperativos/HinchasEntraron.txt", salidaDelPrograma.retornarHinchasEntraror());
       ManejadorArchivosGenerico.escribirArchivo("src/SistemasOperativos/HinchasNoEntraron.txt", salidaDelPrograma.retornarHinchasNoEntraror());
   }
   
   public void hacerEntrarGente(){
       
       try{
            this.semaforoFin.acquire();
            this.miReloj.start();
            this.amsterdam.start();
            this.colombes.start();
            this.olimpica.start();
            this.america.start();
            this.centroDeOperaciones.start();
            this.semaforoFin.acquire();
            imprimirReporte();
            System.exit(0);
              } catch(InterruptedException ex){
                      ex.printStackTrace();
                       System.out.println("Error");
            }
            
   }
}
