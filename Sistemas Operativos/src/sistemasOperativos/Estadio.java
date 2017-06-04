/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasOperativos;

/**
 *
 * @author Usuario
 */

public class Estadio {
    
    private String nombre;
    private FuncionarioEntrada[] funcionarios;
    public Hinchada[] hinchadas;
    

    public Estadio(String nombre, FuncionarioEntrada[] funcionarios, Hinchada[] hinchadas){
    this.nombre = nombre;
    this.funcionarios = funcionarios;
    this.hinchadas = hinchadas;
    }
    
    public void entrarGente(){
        for(Hinchada hinchada: hinchadas){
            hinchada.start();
        }
    }
    
    
    
    
}
