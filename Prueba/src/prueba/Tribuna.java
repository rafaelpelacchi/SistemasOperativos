/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.concurrent.Semaphore;

public class Tribuna  extends Thread{
  private String nombre;
  private Semaphore semaforoReloj;
  private Hincha[] hinchasEntrada;
  private int cantidadFuncionarios;
  private ControlTribuna controlDeTribuna;  
  int indice;
  
  public Tribuna(String nombre, Semaphore semaforoReloj, String documentoHinchas, 
          int cantidadFuncionarios, ControlTribuna controlDeTribuna){
        this.nombre = nombre;
        this.hinchasEntrada = cargarHinchas(documentoHinchas);    
        this.cantidadFuncionarios = cantidadFuncionarios;
        this.semaforoReloj = semaforoReloj;
        this.controlDeTribuna = controlDeTribuna;
        this.indice = 0;
  }
  
  private Hincha[] cargarHinchas(String documentoHinchas){
  //Leo los hinchas de una hinchada y los cargo en el Array
         String[] nombres  = ManejadorArchivosGenerico.leerArchivo(documentoHinchas);
         Hincha[] hinchasEntrada = new Hincha[nombres.length];
         String[] lineaActual;
         
         int i=0;
         String nombre ;
         String apellido;
         String cedula;
         int prioridad; // 1- Embarazada 2- Socios 3- Hinchas Normales
         int hora;
         boolean accede;
         boolean leido;
         
         
         //Hay que meter a los hinchas dentro de sus correspondientes prioridad
         for(String persona : nombres){
            lineaActual=persona.split(",");
            nombre = lineaActual[0];
            apellido= lineaActual[1];
            cedula = lineaActual[2];
            prioridad = Integer.parseInt(lineaActual[3]); // 1- Embarazada 2- Socios 3- Hinchas Normales
            hora = Integer.parseInt(lineaActual[4]);
            accede = Boolean.parseBoolean(lineaActual[5]);
            leido = Boolean.parseBoolean(lineaActual[6]);
            hinchasEntrada[i] = new  Hincha(nombre,apellido,cedula,accede,leido,hora,prioridad);
            i++;
        }
         
         return hinchasEntrada;
  }
    
  // 1- Leer con las camaras a los hinchas
  // 2 Dejar pasar tantos hinchas como funcionarios tenga
  @Override
  public void run(){  
      
      while(!controlDeTribuna.getTermino()){
         try{
                controlDeTribuna.getSemaphore().acquire();
            }
                   catch(InterruptedException ex){
                      ex.printStackTrace();
                       System.out.println("Error");
            }
            System.out.println("Se Entro la persona de nombre " + hinchasEntrada[indice].getNombre() + " por la tribuna " + this.nombre);
            indice++;
            if(indice==hinchasEntrada.length){ this.controlDeTribuna.setTermino(true);}
            controlDeTribuna.getSemaphoreReloj().release();
            //this.semaforoReloj.release();
        }
  }
  
  
}
