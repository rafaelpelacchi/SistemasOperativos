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
public class Hincha{
    private String nombre;
    private String apellido;
    private String cedula;
    private int prioridad; // 1- Embarazada 2- Socios 3- Hinchas Normales
    private int hora;
    private boolean accede;
    private boolean leido;
    
    
     /**
  * Constructor de clase Hincha
  *
  * @param nombre Nombre de hincha.
  * @param apellido Apellido de hincha.
  * @param cedula Cedula de hincha.
  * @param accede Campo que indica si un hincha puede entrar o no.
  * @param leido Campo que indica si las camaras leyeron o no la cara del hincha.
  * @param hora Hora en la que el hincha entrará.
  * @param prioridad Prioridad que tiene el hincha: 1-Embarazada 2-Socio 3-Hincha Normal.
  */
    public Hincha(String nombre,String apellido, String cedula, boolean accede, boolean leido, int hora, int prioridad){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.prioridad = prioridad;
        this.hora = hora;
        this.accede = accede;
        this.leido = leido;
    }
    
    // Properties
    public String getNombre(){ return this.nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    
    public String getApellido(){ return this.apellido; }
    public void setApellido(String apellido){ this.apellido = apellido; }
    
    public String getCedula(){ return this.cedula; }
    public void setCedula(String cedula){ this.cedula=cedula; }
    
    public int getPrioridad(){ return this.prioridad; }
    public void setPrioridad(int prioridad){ this.prioridad = prioridad;}
    
    public int getHora(){ return this.hora; }
    public void setHora(int hora){ this.hora = hora; }
    
    public boolean getAccede(){ return this.accede; }
    public void setAccede(boolean accede){ this.accede = accede; }
    
    public boolean getLeido(){ return this.leido; }
    public void setLeido(boolean leido){ this.leido = leido;}
    
    
}
