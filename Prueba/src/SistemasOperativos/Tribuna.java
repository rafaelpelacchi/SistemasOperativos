/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemasOperativos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Tribuna  extends Thread{
  private String nombre;
  private int cantidadFuncionarios;
  private ControlTribuna controlDeTribuna;  
  private ArrayList<Hincha> prioridadEmbarazada;
  private ArrayList<Hincha> prioridadSocio;
  private ArrayList<Hincha> prioridadHincha;
  private Tiempo tiempo;
  int indiceEmbarazada;
  int indiceSocio;
  int indiceHincha;
  int indiceEmbarazadaCamara;
  int indiceSocioCamara;
  int indiceHinchaCamara;
  int contadorSocios;
  CentroOperaciones centroDeOperaciones;
  Camara[] misCamaras;
  
  public Tribuna(String nombre, String documentoHinchas, 
          int cantidadFuncionarios,int cantFuncionarios,int cantCamaras, ControlTribuna controlDeTribuna, Tiempo tiempo,CentroOperaciones centroDeOperaciones){
        this.nombre = nombre;
        this.cantidadFuncionarios = cantFuncionarios;
        this.controlDeTribuna = controlDeTribuna;
        this.tiempo = tiempo;
        this.centroDeOperaciones = centroDeOperaciones;
        this.prioridadEmbarazada = new ArrayList<Hincha>();
        this.prioridadSocio = new ArrayList<Hincha>();
        this.prioridadHincha = new ArrayList<Hincha>();
        cargarHinchas(documentoHinchas);
        misCamaras = new Camara[cantCamaras];
        for(int i =0 ; i <misCamaras.length ;i ++){
            misCamaras[i] = new Camara(centroDeOperaciones);
        }
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
                Hincha hinchaAuxiliar;
                for(int i = 0 ; i < this.misCamaras.length ;i++  ){
                    hinchaAuxiliar = elegirHinchaCamara();
                    if (hinchaAuxiliar.getNombre() != null){
                         this.misCamaras[i].procesarImagen(hinchaAuxiliar);                    
                    }
                }
                // 
                if(tiempo.getTiempo()%2 == 0)
                {
                for(int i = 0 ; i < this.cantidadFuncionarios ;i++  ){
                    hinchaAuxiliar = elegirHincha();
                    if (hinchaAuxiliar.getNombre() != null && puedeEntrar(hinchaAuxiliar)){
                     hinchaAuxiliar.setHoraEntradaReal(this.tiempo.getTiempo());
                    controlDeTribuna.dejarEntrarHincha(hinchaAuxiliar, this.nombre);                    
                    System.out.println("Entro " + hinchaAuxiliar.getNombre() + " " + this.nombre + " " + Boolean.toString(hinchaAuxiliar.getLeido()));
                    }
                    else{
                        if(hinchaAuxiliar.getNombre() != null && !puedeEntrar(hinchaAuxiliar)){
                         hinchaAuxiliar.setHoraEntradaReal(this.tiempo.getTiempo());
                         controlDeTribuna.denegarEntradaHincha(hinchaAuxiliar, this.nombre);
                         System.out.println("No entro " + hinchaAuxiliar.getNombre()+ " " + this.nombre + " " + Boolean.toString(hinchaAuxiliar.getLeido()));}
                    }
                }
                }
                controlDeTribuna.getSemaphoreReloj().release();
              } catch(InterruptedException ex){
                      ex.printStackTrace();
                       System.out.println("Error");
            }
    }
      switch(this.nombre){
          case "Amsterdam": this.centroDeOperaciones.setTerminoAmsterdam(true);break;
          case "Colombes":  this.centroDeOperaciones.setTerminoColombes(true);break;
          case "Olimpica":  this.centroDeOperaciones.setTerminoOlimpica(true);break;
          case "America":   this.centroDeOperaciones.setTerminoAmerica(true);break;
      }
  }

  public Hincha elegirHincha()
  {
      Hincha hinchaElegido = new Hincha();      
         if (!prioridadEmbarazada.isEmpty() && indiceEmbarazada < prioridadEmbarazada.size() && (prioridadEmbarazada.get(indiceEmbarazada).getHora() - tiempo.getTiempo()) <= 0){
             hinchaElegido=prioridadEmbarazada.get(indiceEmbarazada);
             indiceEmbarazada++;
         }else if (!prioridadSocio.isEmpty() && indiceSocio < prioridadSocio.size() && (prioridadSocio.get(indiceSocio).getHora() - tiempo.getTiempo()) <= 0 && contadorSocios < 3){
             hinchaElegido=prioridadSocio.get(indiceSocio);indiceSocio++;
             contadorSocios++;
         }
         else if (!prioridadHincha.isEmpty() && indiceHincha < prioridadHincha.size() && (prioridadHincha.get(indiceHincha).getHora() - tiempo.getTiempo()) <= 0){
             hinchaElegido=prioridadHincha.get(indiceHincha);indiceHincha++;
             contadorSocios = 0;
         }          
            if(indiceEmbarazada == prioridadEmbarazada.size() && indiceSocio == prioridadSocio.size() && indiceHincha  == prioridadHincha.size()){ this.controlDeTribuna.setTermino(true);}
            
      return hinchaElegido;
  }
  
    public Hincha elegirHinchaCamara()
  {
      Hincha hinchaElegido = new Hincha();      
         if (!prioridadEmbarazada.isEmpty() && indiceEmbarazadaCamara < prioridadEmbarazada.size() && (prioridadEmbarazada.get(indiceEmbarazadaCamara).getHora() - tiempo.getTiempo()) <10){
             hinchaElegido=prioridadEmbarazada.get(indiceEmbarazadaCamara);
             indiceEmbarazadaCamara++;
         }else if (!prioridadSocio.isEmpty() && indiceSocioCamara < prioridadSocio.size() && (prioridadSocio.get(indiceSocioCamara).getHora() - tiempo.getTiempo()) <10){
             hinchaElegido=prioridadSocio.get(indiceSocioCamara);indiceSocioCamara++;
         }
         else if (!prioridadHincha.isEmpty() && indiceHinchaCamara < prioridadHincha.size() && (prioridadHincha.get(indiceHinchaCamara).getHora() - tiempo.getTiempo()) < 10){
             hinchaElegido=prioridadHincha.get(indiceHinchaCamara);indiceHinchaCamara++;
         }          
      return hinchaElegido;
  }
    
    public boolean puedeEntrar(Hincha hinchaActual){
        if(!hinchaActual.getAccede() && hinchaActual.getLeido()) return false;
        else return true;
    }
}
