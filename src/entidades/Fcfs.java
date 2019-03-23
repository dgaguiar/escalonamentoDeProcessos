/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 *
 * @author Daianne
 */
public class Fcfs {
    
    public double p1; 
    public double p2; 
    
    static double [] turnaroundSJF;
    static double [] tempoesperaSJF;
    
    public void Fcfs (){
        
        Scanner scan = new Scanner(System.in);
        int n, entrada, tempoAtual;
        double tempoExecucao, tempoEspera, turnaround;
        ArrayList processos, tempoChegada, surto, tempofinal, tempoinicial;
        int teste=0;
        String formato, saida;
        DecimalFormat decimal = new DecimalFormat();
        
        
        System.out.println("Quantos processos deseja armazenar? ");
        n = scan.nextInt();
        
        while (n != 0){
            teste++; 
            processos = new ArrayList();
            tempoChegada = new ArrayList();
            surto = new ArrayList();
            tempofinal = new ArrayList();
            tempoinicial = new ArrayList();
            tempoEspera = 0; 
            tempoExecucao = 0; 
            turnaround = 0; 
            
            for (int i = 1; i <= n; i++){
                System.out.println("Qual o tempo de chegada do processo p"+i);
                entrada = scan.nextInt();
                tempoChegada.add(entrada);
                System.out.println("Qual o surto do processo "+i + " ? ");
                entrada = scan.nextInt();
                surto.add(entrada);
                processos.add(i);
            }
            tempoAtual = (int) tempoChegada.get(0);
            
            for (int i = 0; i < n; i++){
                if ((int) tempoChegada.get(i) > tempoAtual){
                    tempoAtual = (int) tempoChegada.get(i);
                }
                tempoinicial.add(tempoAtual);
                tempoAtual += (int) surto.get(i);
                tempofinal.add(tempoAtual);
            }   
            
            for (int i = 0; i < n; i++){
                tempoExecucao += (int) tempofinal.get(i) - (int) tempoChegada.get(i);
                tempoEspera += (int) tempoinicial.get(i) - (int) tempoChegada.get(i);
            }
            System.out.println("Processamento - PART"+teste);
            System.out.println(" |          Escalonamento FCFS               |");
            
            for (int i = 0; i < n; i++){
                turnaround = (int) tempofinal.get(i) - (int) tempoChegada.get(i);
                formato = decimal.format(turnaround);
                saida = "|Turn around| :" +i + ": "+formato+ "ms";
                saida = saida.replace(".",",");
                System.out.println(saida); 
            }  
            System.out.println("|---------------------------------------|");
            tempoExecucao = tempoExecucao / n;
            tempoEspera = tempoEspera / n;
            
            formato = decimal.format(tempoExecucao);
            saida = "Tempo médio de execução: "+ formato +"ms";
            saida = saida.replace(".", ",");
            System.out.println(saida);
            
            formato = decimal.format(tempoEspera);
            saida = "Tempo médio de espera: "+ formato +"ms";
            saida = saida.replace(".", ",");
            System.out.println(saida);
            
            System.out.println("|------------- GRAFICO DE GANT---------------|");
            for (int i = 0; i < n; i++){
                System.out.println("P" + processos.get(i) + " ");
            }
            
            System.out.println();
            System.out.println("Quantos processo deseja armazenar?");
            n = scan.nextInt();
        }   
    }       
}

