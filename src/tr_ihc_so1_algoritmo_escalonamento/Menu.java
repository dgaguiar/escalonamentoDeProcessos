/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr_ihc_so1_algoritmo_escalonamento;

import entidades.Fcfs;
import entidades.RoundRobin;
import entidades.Sjf;
import entidades.SjfPreemptivo;
import javax.swing.JOptionPane;

/**
 *
 * @author Daianne
 */
public class Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Fcfs fcfs = new Fcfs();
        Sjf sjf = new Sjf();
        SjfPreemptivo sjfpreemptivo = new SjfPreemptivo();
        RoundRobin rr = new RoundRobin();
        
        
        int escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha o algoritmo: \n"
                + "1 . FCFS\n"
                + "2 . SJF (não preemptivo\n"
                + "3 . Algoritmo de prioridade\n"
                + "4 . Round Robin\n"));
        
        switch (escolha){
            case 1 : 
                fcfs.Fcfs();
                
                break;
            case 2 :
                sjf.Stf();
                
                break;
            case 3 :
                sjfpreemptivo.SJFPreemptivo();
                
                break;
            case 4 :
                rr.RoundRobin();
                
                break;
            default: 
                System.out.println("Opção Inválida!!");
                break;
        }
    }
    
}

