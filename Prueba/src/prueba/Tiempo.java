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
public class Tiempo {
    
    private int tiempoActual;
    
    public Tiempo(int tiempoActual){
        this.tiempoActual = tiempoActual;
    }
    
    public void aumentarTiempo(){
        tiempoActual++;
    }
    
    public int getTiempo(){
        return this.tiempoActual;
    }
         
}
