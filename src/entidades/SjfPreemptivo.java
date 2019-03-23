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
public class SjfPreemptivo {
     public void SJFPreemptivo(){
        
        Scanner scanner = new Scanner(System.in);
        int X, exec, entrad, tempoAtual, numProcessos, idProcAtual;
        ArrayList entrada, burst, processo, prioridade, cpentrada;
        int [] tempInicial, tempFinal;
        String ordemExecucao;
        int teste = 0;
        String format, exit;
        DecimalFormat decimal = new DecimalFormat("0.00");
        
        System.out.println("Digite a quantidade de processos: ");
        X = scanner.nextInt();
        
        while (X != 0) {
            teste++;
            processo = new ArrayList();
            entrada = new ArrayList();
            burst = new ArrayList();
            prioridade = new ArrayList();
            
            for (int i = 0; i < X; i++) {
                System.out.println("Digite o tempo de entrada de P"+(i+1));
                entrad = scanner.nextInt();
                entrada.add(entrad);
                System.out.println("Digite o burst de P"+(i+1));
                entrad = scanner.nextInt();
                burst.add(entrad);
                System.out.println("Digite a prioridade de P"+(i+1));
                entrad = scanner.nextInt();
                prioridade.add(entrad);
            }
            tempInicial = new int[X];
            tempFinal = new int[X];
            cpentrada = (ArrayList) entrada.clone();
            ordemExecucao = "";
            tempoAtual = (int) entrada.get(0);

            numProcessos = X;
            while (numProcessos > 0) {
                processo = new ArrayList(); 
                for (int i = 0; i < X; i++) {
                    if ((int)entrada.get(i) != -1 && (int)entrada.get(i) <= tempoAtual ) {
                        processo.add(i);
                    }
                }
                if(processo.isEmpty()) {
                    tempoAtual++;
                } else {
                    exec = (int)processo.get(0);
                    for (int i = 0; i < processo.size(); i++) {
                        idProcAtual = (int)processo.get(i);
                        if ((int)prioridade.get(idProcAtual) < (int)prioridade.get(exec)) {
                            exec = (int)processo.get(i);
                        }
                    }
                    tempInicial[exec] = tempoAtual;
                    tempoAtual += (int)burst.get(exec);
                    tempFinal[exec] = tempoAtual;
                    entrada.set(exec, -1);
                    ordemExecucao += "P" + (exec + 1) + " ";
                    numProcessos--;
                }
            }
            double tempExec = 0, tempEspera = 0;
            for (int i = 0; i < X; i++) {
                tempExec += tempFinal[i] - (int)cpentrada.get(i);
                tempEspera += tempInicial[i] - (int)cpentrada.get(i);
            }
            tempExec = tempExec / X;
            tempEspera = tempEspera / X;
            
            format = decimal.format(tempExec);
            exit = "Tempo médio de execução: " + format + "s";
            exit = exit.replace(".", ".");
            System.out.println(exit);
            
            format = decimal.format(tempEspera);
            exit = "Tempo médio de espera: " + format + "s";
            exit = exit.replace(".", ".");
            System.out.println(exit);
            
            return;
        }
    } 
}
