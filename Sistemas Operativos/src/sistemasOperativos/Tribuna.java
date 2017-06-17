/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Tribuna extends Thread {
    
    private String nombre;
    
    // Las embarazadas nunca pierden la prioridad.
    // Cada 3 socios (Si hay) entra un hincha Normal.
    private Hincha[] embarazadas; // Prioridad 1
    private Hincha[] socios; // Prioridad 2
    private Hincha[] hinchasNormales; // Prioridad 1
    private Procesadores[] procesadorTribuna;
    private Semaphore semaforoEmbarazada;
    private Semaphore semaforoSocios;
    private Semaphore semaforoNormales;
    private Semaphore semaforoRetirandoHincha;
    private CentroOperaciones centroDeOperaciones;
    private int indiceEmbarazada;
    private int indiceSocios;
    private int indiceHinchasNormales;
    private int horaActual;
    private int cantidadSocios; //Variable se utiliza para controlar la relación de acceso socios - hinchas normales (3-1)
    
    
    public Tribuna(String nombre, Hincha[] embarazadas, Hincha[] socios,Hincha[] hinchasNormales
            , CentroOperaciones centroDeOperaciones){
            this.nombre = nombre;
            this.embarazadas = embarazadas;
            this.socios=socios;
            this.hinchasNormales=hinchasNormales;
            this.centroDeOperaciones= centroDeOperaciones;

            //Se crean los semaforos para que 2 procesadores no accedan a un mismo hincha a la vez
            this.semaforoEmbarazada = new Semaphore(1);
            this.semaforoSocios = new Semaphore(1);
            this.semaforoNormales = new Semaphore(1);
            this.semaforoRetirandoHincha = new Semaphore(1);
            
            this.indiceEmbarazada=0;
            this.indiceSocios = 0;
            this.indiceHinchasNormales=0;                   

            //Se inicializan los procesadores y se les envía el centro de operaciones, los procesadores van a 
            //agregar a las imagenes detectadas al centro de operaciones para que este tome la imagen y si esta a tiempo
            //Informa que no lo dejen pasar
            for(Procesadores aux: procesadorTribuna){
              aux = new Procesadores(centroDeOperaciones);
             }
    }
    
    
    //Metodo de la tribuna que activa los procesadores, a cada procesador se le setea el hincha
    //Luego de eso se utiliza el start del procesador para agregarlo al centro de operaciones
    //Este método finaliza cuando no quedan hinchas por entrar.
    @Override
    public void run(){
        while(indiceEmbarazada<embarazadas.length && indiceSocios<socios.length && indiceHinchasNormales<hinchasNormales.length)
        for(Procesadores aux: procesadorTribuna){
            try{
                this.semaforoRetirandoHincha.acquire();
                aux.setHincha(eleccionHincha());
                this.semaforoRetirandoHincha.release();
                aux.start();
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
            }
                
            
        }            
    }
    
    //Hay que implementar este metodo que es el más importante. El metodo toma un hincha de uno de los 3
    //Buffers, las embarazadas entran de una y de cada 3 socios entra un hincha norma
    public Hincha eleccionHincha(){
        Hincha retorno = new Hincha();
        if(indiceEmbarazada< embarazadas.length && enAlcanceDeCamara(embarazadas[indiceEmbarazada])){
            indiceEmbarazada++;
           retorno = embarazadas[indiceEmbarazada];
        }
        else{
            if(indiceSocios < socios.length && enAlcanceDeCamara(socios[indiceSocios])){
                if(indiceHinchasNormales <  hinchasNormales.length && enAlcanceDeCamara(hinchasNormales[indiceHinchasNormales]))
                {
                    if(cantidadSocios <=3){
                        cantidadSocios ++;
                        indiceSocios ++;
                        retorno = socios[indiceSocios];
                    } 
                    else {
                            cantidadSocios =0;
                            indiceHinchasNormales++;
                            retorno = hinchasNormales[indiceHinchasNormales];
                          }
                }
            }
            else{
                cantidadSocios=0;
                indiceHinchasNormales ++ ;
                retorno = hinchasNormales[indiceHinchasNormales];
            }
        }
        return retorno;
    }
    
    //Si la persona esta en un rango de tiempo mayor que 0 y menor o igual a 11 significa que esta al alcance de las caaras
    // Entonces esa persona es agarrada por uno de los procesadores para ver si puede entrar
    public boolean enAlcanceDeCamara( Hincha hinchaListo){
    if(horaActual-hinchaListo.getHora() <= 10 && 0 <= horaActual-hinchaListo.getHora()) return true;
    else return false;
    }
    
}
