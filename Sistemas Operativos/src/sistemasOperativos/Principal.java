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
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        String[] nombres  = ManejadorArchivosGenerico.leerArchivo("src/sistemasOperativos/Funcionarios");
        FuncionarioEntrada[] funcionarios = new FuncionarioEntrada[nombres.length];
        int i =0;
        String[] auxiliar;
        for(String persona : nombres){
            auxiliar=persona.split(",");
            funcionarios[i] = new  FuncionarioEntrada(auxiliar[0],auxiliar[1]);
            i++;
        }
        
        //America
        nombres  = ManejadorArchivosGenerico.leerArchivo("src/sistemasOperativos/America.txt");
        Hincha[] hinchasAmerica = new Hincha[nombres.length];
        i =0;
        boolean temporal;
        Hincha actual;
        for(String persona : nombres){
             auxiliar= persona.split(",");
             System.out.println(i);
             temporal=Boolean.valueOf(auxiliar[2]);
             actual= new  Hincha(auxiliar[0],auxiliar[1],temporal);
            hinchasAmerica[i] = actual;
            i++;
        }
         Tribuna america= new Tribuna("America",hinchasAmerica, funcionarios);
       Tribuna colombes= new Tribuna("Colombes",hinchasAmerica, funcionarios);
       Tribuna olimpica= new Tribuna("Olimpica",hinchasAmerica, funcionarios);
       Tribuna amsterdam= new Tribuna("Amsterdam",hinchasAmerica, funcionarios);
       
        Tribuna[] hinchadas ={america,colombes,olimpica,amsterdam};
        
        Estadio estadioCentenario = new Estadio("Centenario", funcionarios, hinchadas);
        estadioCentenario.entrarGente();
       */
    }
    
}
