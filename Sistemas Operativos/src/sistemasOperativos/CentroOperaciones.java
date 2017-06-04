/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class CentroOperaciones {
    private boolean semaforo;
    private LinkedList<Hincha> fifo;

    public CentroOperaciones(){
        fifo = new LinkedList<Hincha>();
        this.semaforo = false;
    }
    public void reconocer(Hincha entrando){
        if(entrando.geAccede())
            System.out.println("Entró " + entrando.getNombre());
    }
}

