/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

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
   
   public Estadio(String nombre,Tribuna amsterdam,Tribuna colombes,Tribuna olimpica,Tribuna america, Reloj miReloj){
       this.nombre = nombre;
       this.amsterdam = amsterdam;
       this.colombes = colombes;
       this.olimpica = olimpica;
       this.america = america;
       this.miReloj = miReloj;
   }
   
   
   
   public void hacerEntrarGente(){
        this.miReloj.start();
       this.amsterdam.start();
       this.colombes.start();
        this.olimpica.start();
       this.america.start();
            
   }
}
