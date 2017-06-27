/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Usuario
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        //Declaro un semaforo por tribuna (Se inicializan en 0 para que esperen a que el reloj les diga cuando pueden actuar)
         Semaphore semaforoAmsterdam = new Semaphore(0);
         Semaphore semaforoColombes = new Semaphore(0);
         Semaphore semaforoOlimpica = new Semaphore(0);
         Semaphore semaforoAmerica = new Semaphore(0);
         
         Semaphore semaforoAmsterdamReloj = new Semaphore(1);
         Semaphore semaforoColombesReloj = new Semaphore(1);
         Semaphore semaforoOlimpicaReloj = new Semaphore(1);
         Semaphore semaforoAmericaReloj = new Semaphore(1);
         
         
         //Declaro mis controladores de Tribunas(Se crean con el valor de Falso ya que marcaran 
         //cuando no haya mas hinchas para procesar) tambien se les pasa el semaforo para habilitar
         //el método run de las tribunas.
         Salida salidaDatos = new Salida();
         ControlTribuna datosAmsterdam = new ControlTribuna(false, semaforoAmsterdam,semaforoAmsterdamReloj, salidaDatos);
         ControlTribuna datosColombes = new ControlTribuna(false,semaforoColombes,semaforoColombesReloj, salidaDatos);
         ControlTribuna datosOlimpica = new ControlTribuna(false, semaforoOlimpica,semaforoOlimpicaReloj, salidaDatos);
         ControlTribuna datosAmerica = new ControlTribuna(false, semaforoAmerica, semaforoAmericaReloj, salidaDatos);
         
         Tiempo tiempoActual = new Tiempo(-1);
         
         //Declaro mi reloj, dandole los semaforos de cada hinchada junto a sus 
         Reloj miReloj = new Reloj(datosAmsterdam,datosColombes,datosOlimpica,datosAmerica, tiempoActual);
         Tribuna amsterdam = new Tribuna("Amsterdam","src/prueba/hinchasAmsterdam.txt", 4, datosAmsterdam, tiempoActual);
         Tribuna colombes = new Tribuna("Colombes","src/prueba/hinchasColombes.txt", 4, datosColombes, tiempoActual);
         Tribuna olimpica = new Tribuna("Olimpica","src/prueba/hinchasOlimpica.txt", 4, datosOlimpica, tiempoActual);
         Tribuna america = new Tribuna("America","src/prueba/hinchasAmerica.txt", 4, datosAmerica, tiempoActual);
         
         Estadio estadioCentenario = new Estadio("Estadio Centenario",amsterdam,colombes,olimpica,america,miReloj);
         
         estadioCentenario.hacerEntrarGente();
    }
    
}
