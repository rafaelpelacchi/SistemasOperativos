/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Salida {
    private Semaphore semaforoSalida;
    private LinkedList<Hincha> hinchasQueEntraron;
    private LinkedList<Hincha>  hinchasQueNoEntraron;
    
    public Salida(){
        this.semaforoSalida = new Semaphore(1);
        hinchasQueEntraron= new LinkedList<Hincha>();
        hinchasQueNoEntraron= new LinkedList<Hincha>();
    }
    
    public void hacerEntrarHincha(Hincha entrarHincha){
        hinchasQueEntraron.add(entrarHincha);
    }
    
    public void noDejarPasarHincha(Hincha entrarHincha){
        hinchasQueNoEntraron.add(entrarHincha);
    }

    public Semaphore getSemaforoSalida() {
        return semaforoSalida;
    }

    public void setSemaforoSalida(Semaphore semaforoSalida) {
        this.semaforoSalida = semaforoSalida;
    }
    
    
}
