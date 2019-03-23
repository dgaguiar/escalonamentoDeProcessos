/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Daianne
 */
public class Sjf {
    public void Stf (){        
        Scanner scan = new Scanner(System.in);
        int n, entrada; 
        ArrayList processos, chegada, cpchegada = new ArrayList(), surto;
        int[] tempofinal = new int[1], tempoinicial = new int[1];
        int idProcessoAtual; 
        String ordemExecucao = "", formato, saida;
        double tempoEspera, tempoExecucao, turnaround; 
        int contTeste = 0; 
        DecimalFormat decimal = new DecimalFormat("0.00");
        
        System.out.println("Quantos processos deseja armazenar?");
        n = scan.nextInt();
        
        while (n != 0){
            contTeste++;
            
            ordemExecucao = "";
            processos = new ArrayList();
            chegada = new ArrayList();
            surto = new ArrayList();
            tempofinal = new int[n];
            tempoinicial = new int[n];
            
            for (int i = 0; i < n; i++){
                System.out.println("Tempo de chegada do P"+(i+1)+ " :");
                entrada = scan.nextInt();
                chegada.add(entrada);
                System.out.println("Tempo de surto de P "+(i+1)+" :");
                entrada = scan.nextInt();
                surto.add(entrada);   
            }   
            
            cpchegada = (ArrayList) surto.clone();
            int execucao;
            int qtdeprocessos = n; 
            int tempoAtual = (int) chegada.get(0);
            while (qtdeprocessos > 0){
                processos = new ArrayList();
                for (int i=0; i <n; i++){
                    if ((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual){
                        processos.add(i);
                    }
                }
                if (processos.isEmpty()){
                    tempoAtual++;
                } else {
                    execucao = (int) processos.get(0);
                    for (int i = 0; i < processos.size(); i++){
                        idProcessoAtual = (int) processos.get(i);
                        if ((int) surto.get(idProcessoAtual) < (int) surto.get(execucao)){
                            execucao = (int) processos.get(i);
                        }
                    }
                    tempoinicial[execucao] = tempoAtual;
                    tempoAtual += (int) surto.get(execucao);
                    tempofinal[execucao] = tempoAtual;
                    chegada.set(execucao, -1);
                    ordemExecucao += "p" + (execucao + 1) + " ";
                    qtdeprocessos--;
                }               
            }
            tempoExecucao = 0; 
            tempoEspera = 0;
            for (int i = 0; i < n; i++){
                tempoExecucao += tempofinal[i] - (int)cpchegada.get(i);
                tempoEspera += tempoinicial[i] - (int)cpchegada.get(i);
            }
            System.out.println("PROCESSAMENTO - PARTE "+ contTeste);
            for (int i = 0; i < n; i++){
                turnaround = (int) tempofinal[i] - (int)cpchegada.get(i);
                formato = decimal.format(turnaround);
                saida = "|TurnAround | P" +i+" :"+ formato +" ms";
                saida = saida.replace(".",",");
                System.out.println(saida);
            }
            
            tempoExecucao = tempoExecucao / n; 
            tempoEspera = tempoEspera / n; 
            
            formato = decimal.format(tempoExecucao);
            saida = "Rempo médio de execução: " + formato + "s";
            saida = saida.replace(".",",");
            System.out.println(saida);
        
            formato = decimal.format(tempoEspera);
            saida = "Rempo médio de execução: " + formato + "s";
            saida = saida.replace(".",",");
            System.out.println(saida);
            
            System.out.println(ordemExecucao);
            System.out.println();
            System.out.println("Deseja armazenar quantos processos?");
            n = scan.nextInt();
        }
    }
}
