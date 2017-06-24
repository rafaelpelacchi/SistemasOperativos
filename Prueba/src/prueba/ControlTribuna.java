
package prueba;

import java.util.concurrent.Semaphore;

/*
 * Esta clase es usada tanto por la tribuna como por el reloj, una vez que la 
 * todos los hinchas hayan pasado por una tribuna se cambiará el valor "termino"
 * haciendo que el reloj pare.
 */
public class ControlTribuna {
    private boolean termino;
    private Semaphore semaforoTribuna;
    private Semaphore semaforoTribunaReloj;
 
    public ControlTribuna(boolean termino, Semaphore semaforoTribuna, Semaphore semaforoTribunaReloj){
        this.termino = termino;
        this.semaforoTribuna = semaforoTribuna;
        this.semaforoTribunaReloj = semaforoTribunaReloj;
    }
    
    public boolean getTermino(){ return this.termino; }    
    public void setTermino(boolean termino){this.termino = termino;}
    
    public Semaphore getSemaphore(){ return this.semaforoTribuna; }
    public Semaphore getSemaphoreReloj(){ return this.semaforoTribunaReloj; }
}
