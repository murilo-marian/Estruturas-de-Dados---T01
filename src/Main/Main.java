package Main;

import Voo.Aviao;
import Voo.Espera;
import Voo.Pista;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int decolagens = 0;
        int pousos = 0;
        int emergencias = 0;
        int precaucao = 0;
        int totalPousos = 0;
        int totalDecolagens = 0;
        int total = 0;

        System.out.println("inicio");

        Espera espera = new Espera();
        Pista pista = new Pista();
        espera.setPista(pista);
        Aviao aviao1 = new Aviao(2);
        Aviao aviao2 = new Aviao(5);
        Aviao aviao3 = new Aviao(1);

        espera.inserirInicio(aviao1);
        espera.inserirFim(aviao2);
        espera.inserirFim(aviao3);

        int tempoEspera = 0;
        int tempoPista = 0;

        while (!espera.vazia() || !pista.estaVazio()) {
            Aviao aviao = new Aviao();
            Random rand = new Random();
            espera.gerarAviao(rand.nextInt(0, 2));
            if (espera.checarEmergencia(3)) {
                System.out.println("--------------------------");
                System.out.println("Realizando pouso de emergencia");
                aviao = espera.pousarEmergencia();
                System.out.println("Pousado avião de ID: " + aviao.getId());
                System.out.println("--------------------------");
                System.out.println("Lista de Espera: " + espera.mostraLista());
                System.out.println("Pista: " + pista.mostraLista());

                tempoEspera += aviao.getTempoDeEspera();
                pousos++;
                decolagens--;
                emergencias++;
                totalPousos++;
            } else if (espera.checarEmergencia(6)) {
                System.out.println("--------------------------");
                System.out.println("Realizando pouso de precaução");
                aviao = espera.pousarEmergencia();
                System.out.println("Pousado avião de ID: " + aviao.getId());
                System.out.println("--------------------------");
                System.out.println("Lista de Espera: " + espera.mostraLista());
                System.out.println("Pista: " + pista.mostraLista());

                tempoEspera += aviao.getTempoDeEspera();
                pousos++;
                decolagens--;
                precaucao++;
                totalPousos++;

            } else if (decolagens > -3 && !espera.vazia()) {
                System.out.println("------------------------------");
                System.out.println("Realizando Pouso");
                aviao = espera.pousar();
                System.out.println("Pousado avião de ID: " + aviao.getId());
                System.out.println("--------------------------");
                System.out.println("Lista de Espera: " + espera.mostraLista());
                System.out.println("Pista: " + pista.mostraLista());

                tempoEspera += aviao.getTempoDeEspera();

                pousos++;
                decolagens--;
                totalPousos++;
            } else {
                aviao = pista.decolar();
                decolagens++;
                pousos--;
                totalDecolagens++;
                tempoPista += aviao.getTempoDeEspera();
            }

            total++;
            espera.gastarCombustivel();

            System.out.println("-----------------");
            System.out.println("Lista de Espera: " + espera.mostraLista());
            System.out.println("Pista: " + pista.mostraLista());
            System.out.println("------------");
            System.out.println("Ratio de Pousos: " + pousos); //ratio de pousos, tanto de emergencia quanto normais
            System.out.println("Ratio de Decolagens: " + decolagens); //ratio de decolagens
            System.out.println("Total de Pousos: " + totalPousos );
            System.out.println("Total de Decolagens: " + totalDecolagens );
            System.out.println("Total de Emergencias: " + emergencias); //total de pousos categorizados como de emergência
            System.out.println("Total de Pousos de Precaução: " + precaucao); //total de pousos entre normais e de emergencia (bandeira amarela)
            System.out.println("Tempo Médio de Espera para Pousar: " + tempoEspera / total); //média de espera
            System.out.println("Tempo Médio de Espera para Decolar: " + tempoPista / total); //média de espera
        }

        System.out.println("fora do loop");
        System.out.println(espera.mostraLista());
        System.out.println(pista.mostraLista());

        //tempo de espera para decolagem
        //arrumar para gas = 0
    }
}
