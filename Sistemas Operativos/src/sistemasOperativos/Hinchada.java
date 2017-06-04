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
public class Hinchada extends Thread {
    
    private String nombre;
    private FuncionarioEntrada[] funcionarios;
    private Hincha[] hinchas;
    
    public Hinchada(String nombre, Hincha[] hinchas,FuncionarioEntrada[] funcionarios){
    this.nombre = nombre;
    this.hinchas = hinchas;
    this.funcionarios = funcionarios;
    }
    
    
    public void Run()
    {
        System.out.println("h");
        int i = 0;
        for(FuncionarioEntrada funcionario : funcionarios){
           funcionario.setHincha(hinchas[i]);
           
           funcionario.start();
        }
    }
    
}
