package Main;

import Voo.Aviao;
import Voo.Espera;
import Voo.Pista;

import java.util.Random;

public class Main {
    static int emergencias = 0;
    static int precaucao = 0;
    static int totalPousos = 0;
    static int totalDecolagens = 0;
    static int total = 0;
    static int tempoEspera = 0;
    static int tempoPista = 0;

    public static void main(String[] args) throws InterruptedException {

        Espera espera = new Espera();
        espera.setId(1);
        Espera espera2 = new Espera();
        espera2.setId(2);
        Pista pista = new Pista();
        Pista pista2 = new Pista();
        espera.setPista(pista);
        espera2.setPista(pista2);
        Aviao aviao1 = new Aviao(2);
        Aviao aviao2 = new Aviao(5);
        Aviao aviao3 = new Aviao(1);

        Aviao aviao4 = new Aviao(4);
        Aviao aviao5 = new Aviao(5);
        Aviao aviao6 = new Aviao(3);

        espera.inserir(aviao1);
        espera.inserir(aviao2);
        espera.inserir(aviao3);

        espera2.inserir(aviao4);
        espera2.inserir(aviao5);
        espera2.inserir(aviao6);

        System.out.println("Iniciando Sistema");
        //while (!espera.vazia() || !espera2.vazia()) { //até esvaziar
        while (true) { //até cair
            Aviao aviao;
            gerarAviao(espera, espera2);
            //Thread.sleep(1000);

            System.out.println("-----------------------------------");
            System.out.println("Sistema da pista/prateleira 1");
            System.out.println("Conteúdo da Prateleira 1: " + espera.mostraLista());
            aviao = pousarDecolar(espera);
            //Thread.sleep(3000);
            if (aviao == null) {
                System.out.println("----------QUEDA----------");
                System.out.println("Queda Detectada na Lista de Espera 1");
                System.out.println("----------QUEDA----------");
                break;
            }

            System.out.println("-----------------------------------");
            System.out.println("Sistema da pista/prateleira 2");
            System.out.println("Conteúdo da Prateleira 2: " + espera2.mostraLista());
            aviao = pousarDecolar(espera2);
            //Thread.sleep(3000);
            if (aviao == null) {
                System.out.println("Queda Detectada na Lista de Espera 2");
                break;
            }

            total++;
            espera.gastarCombustivel();
            espera2.gastarCombustivel();


            //Thread.sleep(1000);
            System.out.println("-----------------------------------");
            System.out.println("Lista de Espera 1: " + espera.mostraLista() + espera.getTamanho());
            System.out.println("Lista de Espera 2: " + espera2.mostraLista() + espera2.getTamanho());
            //Thread.sleep(1000);
            System.out.println("-----------------------------------");
            System.out.println("Pista 1: " + pista.mostraLista());
            System.out.println("Pista 2: " + pista2.mostraLista());
            System.out.println("-----------------------------------");
            //Thread.sleep(1000);
            System.out.println("Total de Pousos: " + totalPousos );
            System.out.println("Total de Decolagens: " + totalDecolagens );
            System.out.println("Total de Emergencias: " + emergencias); //total de pousos categorizados como de emergência
            System.out.println("Total de Pousos de Precaução: " + precaucao); //total de pousos entre normais e de emergencia (bandeira amarela)
            System.out.println("Tempo Médio de Espera para Pousar: " + tempoEspera / total); //média de espera
            System.out.println("Tempo Médio de Espera para Decolar: " + tempoPista / total); //média de espera
            //Thread.sleep(10000);
        }

        System.out.println("Sem mais aviões a pousar ou acidente detectado");
        System.out.println("-----------------------------------");
        System.out.println("Espera 1: " + espera.mostraLista());
        System.out.println("Pista 1: " + pista.mostraLista());

        System.out.println("-----------------------------------");
        System.out.println("Espera 2: " + espera2.mostraLista());
        System.out.println("Pista 2: " + pista2.mostraLista());

    }

    //FUNÇÕES
    //FUNÇÕES
    //FUNÇÕES
    //FUNÇÕES
    //FUNÇÕES
    //FUNÇÕES

    public static void gerarAviao(Espera e1, Espera e2) {
        Random rand = new Random();
        int n = rand.nextInt(0, 3);
        for (int i = 0; i < n; i++) {
            Aviao aviao = new Aviao();
            aviao.setGas(rand.nextInt(2, 21));
            if (n == 1) { //se apenas um, vai pra prateleira com menos aviões
                if (e1.getTamanho() < e2.getTamanho()) {
                    e1.inserir(aviao);
                } else {
                    e2.inserir(aviao);
                }
            } else { //caso 2, cada avião vai para uma diferente
                if (i == 0) {
                    e1.inserir(aviao);
                } else {
                    e2.inserir(aviao);
                }
            }
        }
    }

    public static Aviao pousarDecolar(Espera e) throws InterruptedException {

        Aviao aviao = new Aviao();
        if (e.checarEmergencia(3)) { //nível de emergencia 1
            System.out.println("--------------------------");
            System.out.println("Realizando pouso de emergencia");

            aviao = e.pousarEmergencia();
            if (aviao == null) {
                return null;
            }

            System.out.println("Pousado avião de ID: " + aviao.getId());
            System.out.println("--------------------------");

            tempoEspera += aviao.getTempoDeEspera();
            e.setPousosRatio(e.getPousosRatio() + 1);
            e.getPista().setDecolagensRatio(e.getPista().getDecolagensRatio() - 1);
            emergencias++;
            totalPousos++;
        } else if (e.checarEmergencia(5)) { //cautela
            System.out.println("--------------------------");
            System.out.println("Realizando pouso de precaução");
            aviao = e.pousarEmergencia();
            System.out.println("Pousado avião de ID: " + aviao.getId());

            tempoEspera += aviao.getTempoDeEspera();
            e.setPousosRatio(e.getPousosRatio() + 1);
            e.getPista().setDecolagensRatio(e.getPista().getDecolagensRatio() - 1);
            precaucao++;
            totalPousos++;

        } else if ((e.getPista().getDecolagensRatio() > -1 && !e.vazia()) || totalPousos < 4) { //pouso comum
            System.out.println("------------------------------");
            System.out.println("Realizando Pouso");
            aviao = e.pousar();
            System.out.println("Pousado avião de ID: " + aviao.getId());

            tempoEspera += aviao.getTempoDeEspera();

            e.setPousosRatio(e.getPousosRatio() + 1);
            e.getPista().setDecolagensRatio(e.getPista().getDecolagensRatio() - 1);
            totalPousos++;
        } else if (!(e.getPista().getInicio() == null)) { //decolagem
            System.out.println("-----------------------------------");
            System.out.println("Realizando Decolagem");
            aviao = e.getPista().decolar();
            System.out.println("Decolando avião de ID: " + aviao.getId());
            e.setPousosRatio(e.getPousosRatio() - 1);
            e.getPista().setDecolagensRatio(e.getPista().getDecolagensRatio() + 1);
            totalDecolagens++;
            tempoPista += aviao.getTempoDeEspera();
        }
        //Thread.sleep(3000);
        System.out.println("Lista de Espera " + e.getId() + ": " + e.mostraLista());
        System.out.println("Pista " + e.getId() + ": " + e.getPista().mostraLista());
        return aviao;
    }
}
