/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Tribuna  extends Thread{
  private String nombre;
  private Semaphore semaforoReloj;
  private int cantidadFuncionarios;
  private ControlTribuna controlDeTribuna;  
  private ArrayList<Hincha> prioridadEmbarazada;
  private ArrayList<Hincha> prioridadSocio;
  private ArrayList<Hincha> prioridadHincha;
  private Tiempo tiempo;
  int indiceEmbarazada;
  int indiceSocio;
  int indiceHincha;
  int contadorSocios;
  
  public Tribuna(String nombre, Semaphore semaforoReloj, String documentoHinchas, 
          int cantidadFuncionarios, ControlTribuna controlDeTribuna, Tiempo tiempo){
        this.nombre = nombre;
        this.cantidadFuncionarios = cantidadFuncionarios;
        this.semaforoReloj = semaforoReloj;
        this.controlDeTribuna = controlDeTribuna;
        this.tiempo = tiempo;
        this.prioridadEmbarazada = new ArrayList<Hincha>();
        this.prioridadSocio = new ArrayList<Hincha>();
        this.prioridadHincha = new ArrayList<Hincha>();
        cargarHinchas(documentoHinchas);
  }
  
  public ArrayList<Hincha> getPrioridadEmbarazada(){return this.prioridadEmbarazada;}
  public ArrayList<Hincha> getPrioridadSocio(){return this.prioridadSocio;}
  public ArrayList<Hincha> getPrioridadHincha(){return this.prioridadHincha;}
  
  private void cargarHinchas(String documentoHinchas){
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
         for(Hincha h : hinchasEntrada){
             if (h.getPrioridad() == 1){
                     this.prioridadEmbarazada.add(h);
             }
             else if (h.getPrioridad() == 2){
                 this.prioridadSocio.add(h);
             }
             else {
                 this.prioridadHincha.add(h);
             }
         }
         
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
         
         if (!prioridadEmbarazada.isEmpty() && indiceEmbarazada < prioridadEmbarazada.size() && (prioridadEmbarazada.get(indiceEmbarazada).getHora() - tiempo.getTiempo()) == 0){
             System.out.println("Se Entro la persona de nombre " + prioridadEmbarazada.get(indiceEmbarazada).getNombre() + " por la tribuna " + this.nombre);
             indiceEmbarazada++;
         }else if (!prioridadSocio.isEmpty() && indiceSocio < prioridadSocio.size() && (prioridadSocio.get(indiceSocio).getHora() - tiempo.getTiempo()) <= 0 && contadorSocios < 3){
             System.out.println("Se Entro la persona de nombre " + prioridadSocio.get(indiceSocio).getNombre() + " por la tribuna " + this.nombre);
             indiceSocio++;
             contadorSocios++;
         }
         else if (!prioridadHincha.isEmpty() && indiceHincha < prioridadHincha.size() && (prioridadHincha.get(indiceHincha).getHora() - tiempo.getTiempo()) <= 0){
             System.out.println("Se Entro la persona de nombre " + prioridadHincha.get(indiceHincha).getNombre() + " por la tribuna " + this.nombre);
             indiceHincha++;
             contadorSocios = 0;
         }
          
            if(indiceEmbarazada == prioridadEmbarazada.size() && indiceSocio == prioridadSocio.size() && indiceHincha  == prioridadHincha.size()){ this.controlDeTribuna.setTermino(true);}
            controlDeTribuna.getSemaphoreReloj().release();
            //this.semaforoReloj.release();
        }
  }
  
  
}
