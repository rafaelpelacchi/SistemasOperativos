/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Salida {
    private Semaphore semaforoSalida;
    private Semaphore semaforoDejarPasar;
    private Semaphore semaforoNoDejarPasar;
    private LinkedList<String> hinchasQueEntraron;
    private LinkedList<String>  hinchasQueNoEntraron;
    
    public Salida(){
        this.semaforoSalida = new Semaphore(1);
        this.semaforoDejarPasar = new Semaphore(1);
        this.semaforoNoDejarPasar= new Semaphore(1);
        hinchasQueEntraron= new LinkedList<String>();
        hinchasQueNoEntraron= new LinkedList<String>();
    }
    
    public void hacerEntrarHincha(Hincha entrarHincha, String tribuna){
        try {
            this.semaforoDejarPasar.acquire();
            this.hinchasQueEntraron.add("Entro el hincha " + entrarHincha.getNombre() + " a la hora " + entrarHincha.getHoraEntradaReal() + " por la puerta " + tribuna);
            this.semaforoDejarPasar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void noDejarPasarHincha(Hincha entrarHincha, String tribuna){
        try {
            this.semaforoNoDejarPasar.acquire();
            hinchasQueNoEntraron.add("Se le nego la entrada el hincha " + entrarHincha.getNombre() + " a la hora " + entrarHincha.getHoraEntradaReal() + " por la puerta " + tribuna);
            this.semaforoNoDejarPasar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Semaphore getSemaforoSalida() {
        return semaforoSalida;
    }

    public void setSemaforoSalida(Semaphore semaforoSalida) {
        this.semaforoSalida = semaforoSalida;
    }
    
    public ArrayList<String> retornarHinchasEntraror(){
          ArrayList<String> retorno = new ArrayList<String>(); 
          retorno.addAll(this.hinchasQueEntraron);
          return retorno;
    }
    
    public ArrayList<String> retornarHinchasNoEntraror(){
          ArrayList<String> retorno = new ArrayList<String>(); 
          retorno.addAll(this.hinchasQueNoEntraron);
          return retorno;
    }
}
