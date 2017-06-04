/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

/**
 *
 * @author Usuario
 */

public class Hincha {
    private String nombre;
    private String cedula;
    private boolean accede;
    
    public Hincha(){}
    
    public Hincha(String nombre, String cedula,boolean accede){
        this.nombre = nombre;
        this.cedula = cedula;
        this.accede = accede;
    }
    
    public String getNombre(){ return this.nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    
    public String getCedula(){ return this.cedula; }
    public void setCedula(String cedula){ this.cedula=cedula; }
    
    public boolean geAccede(){ return this.accede; }
    public void setAccede(boolean accede){ this.accede = accede; }
    
    
    
}
