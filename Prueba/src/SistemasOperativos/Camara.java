/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemasOperativos;

/**
 *
 * @author Usuario
 */
public class Camara{
    //El hincha procesando es el hinca que el hilo esta agreagndo al buffer del centro de operaciones
    //Se usa en el método Run
    
    private CentroOperaciones centroDeOperaciones;
    public Camara(CentroOperaciones centroDeOperaciones){
        this.centroDeOperaciones = centroDeOperaciones;
    }
    
    //Envia a las personas al centro de operaciones para que este analice si entra o no.
    public void procesarImagen(Hincha hinchaEntrando){
        centroDeOperaciones.agregarHinchaAProcesar(hinchaEntrando);
    }
      
}
