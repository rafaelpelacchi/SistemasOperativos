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
public class Procesador  extends Thread {
    //El hincha procesando es el hinca que el hilo esta agreagndo al buffer del centro de operaciones
    //Se usa en el método Run
    
    private Hincha procesando;
    
    public Hincha getHincha(){
        return this.procesando;
    }
   
    public void setHincha(Hincha procesando){
        this.procesando = procesando;
    }
    
    //Envia a las personas al centro de operaciones para que este analice si entra o no.
    public void procesarImagen(Hincha hinchaEntrando){
        hinchaEntrando.setLeido(true);
    }
    
    @Override
    public void run(){
        procesarImagen(this.procesando);
    }
    
    
}
