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
public class FuncionarioEntrada extends Thread {
    private String nombre;
    private String cedula;
    private Hincha hinchaActual;
    
    public FuncionarioEntrada(String nombre, String cedula){
        this.nombre = nombre;
        this.cedula = cedula;
        hinchaActual = new Hincha();
    }
    public void setHincha(Hincha hinchaActual){ this.hinchaActual=hinchaActual;}
    
    public String getNombre(){ return this.nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    
    public String getCedula(){ return this.cedula; }
    public void setCedula(String cedula){ this.cedula=cedula; }
    
    @Override
    public void run(){
        if (hinchaActual.geAccede()) {
            System.out.println("Entró" + hinchaActual.getNombre());
        }
         System.out.println("--------------- No entró" + hinchaActual.getNombre());
    }
    
}
