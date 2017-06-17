/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class CentroOperaciones extends Thread{
    private Semaphore estaAgregando;
    private Map<Integer,Hincha> fifo;
    private int i;
    

    public CentroOperaciones(){
        fifo = new HashMap<>();
        this.estaAgregando = new Semaphore(1);
        i = 0;
    }
   
    public void reconocerHincha(Hincha hinchaReconocer){
        fifo.put(i, hinchaReconocer);
        i++;
    }
    
    public void reconocer(Hincha entrando){
        if(entrando.getAccede())
            System.out.println("Entró " + entrando.getNombre());
    }
}

