/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gabri
 */
public class Main {
    public static void main(String[] args) throws IOException {

        int escolha = 0;
        
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("=========================");
            System.out.println("1 - Criar Grafo");       
            System.out.println("2 - Grafos Exemplos");            
            System.out.println("3 - Sair");  
            System.out.println("=========================");

            escolha = in.nextInt();
            in.nextLine();

            if(escolha == 1){
                System.out.println("=========================");
                System.out.println("Pré-Cadastro:");
                System.out.println("Grafo Orientado(true/false): ");
                System.out.println("=========================");
                boolean orientado = in.nextBoolean();
                System.out.println("=========================");
                System.out.println("Grafo Valorado(true/false): ");
                System.out.println("=========================");
                boolean valorado = in.nextBoolean();
                
                if (orientado) {
                    GrafoOrientado o = new GrafoOrientado(valorado);
                    apresentarMenuOrientado(o);
                }
                
                GrafoNaoOrientado no = new GrafoNaoOrientado(valorado);                
                apresentarMenuNaoOrientado(no);

            }


            if (escolha == 2) {
                System.out.println("=========================");
                System.out.println("1 - Grafo Simples");
                System.out.println("2 - MultiGrafo");
                System.out.println("=========================");
                escolha = in.nextInt(); 
                in.nextLine();

                if (escolha == 1) {
                    GrafoNaoOrientado no = new GrafoNaoOrientado(false); 
                    no.gerarAleatorio();
                    apresentarMenuNaoOrientado(no);
                }
                if (escolha == 2) {
                    GrafoOrientado o = new GrafoOrientado(true); 
                    o.gerarAleatorio();
                    apresentarMenuOrientado(o);
                }                    

            }
        }while(escolha != 3);


    }
        
    public static void apresentarMenuOrientado (GrafoOrientado o) throws IOException{
        int escolha = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("=========================");
            System.out.println("1 - Adicionar Vértice");    
            System.out.println("2 - Adicionar Aresta"); 
            System.out.println("3 - Remover Vértice"); 
            System.out.println("4 - Remover Aresta"); 
            System.out.println("5 - Imprimir Grafo"); 
            System.out.println("6 - Informações do Grafo");
            System.out.println("7 - Algoritmos");
            System.out.println("10 - Sair");
            System.out.println("=========================");
            escolha = in.nextInt();
            in.nextLine();

            if(escolha == 1){
                System.out.println("=========================");
                System.out.println("Informe o nome do vértice a ser adicionado");
                System.out.println("=========================");
                String nomev = in.nextLine();
                o.adicionarVertice(nomev);

            }

            if (escolha == 2){
                System.out.println("=========================");
                System.out.println("Informe o vertice de origem da aresta");
                System.out.println("=========================");
                String nomeao = in.nextLine();    
                System.out.println("=========================");
                System.out.println("Informe o vertice de destino da aresta");
                System.out.println("=========================");
                String nomead = in.nextLine();                            

                if(o.isValorado() == true){
                    System.out.println("=========================");
                    System.out.println("Informe o valor da aresta");
                    System.out.println("=========================");
                    int varesta = in.nextInt();
                    in.nextLine();
                    o.adicionarAresta(nomeao, nomead, varesta);
                }

                else{                                
                    o.adicionarAresta(nomeao, nomead, 0);                                
                }
            }

            if(escolha == 3){
                System.out.println("=========================");
                System.out.println("Informe o nome do vértice a ser removido ");
                System.out.println("=========================");
                String nomev = in.nextLine();
                o.removerVertice(nomev);


            }

            if (escolha == 4){
                System.out.println("=========================");
                System.out.println("Informe o vértice de origem da aresta");
                System.out.println("=========================");
                String nomeao = in.nextLine();         
                System.out.println("=========================");
                System.out.println("Informe o vértice de destino da aresta");
                System.out.println("=========================");
                String nomead = in.nextLine();   
                o.removerAresta(nomeao, nomead);                            

            }

            if (escolha == 5){
                do{
                    System.out.println("=========================");
                    System.out.println("Escolha uma das formas de impressão");
                    System.out.println("1 - Matriz Adjacência");
                    System.out.println("2 - Matriz Incidência");
                    System.out.println("3 - Lista Adjacência");
                    System.out.println("4 - Sair");
                    System.out.println("=========================");

                    escolha = in.nextInt();
                    in.nextLine();

                    if (escolha == 1) {
                        System.out.println(o.imprimirMatrizAdjacencia());
                    }
                    if (escolha == 2) {
                        System.out.println(o.imprimirMatrizIncidencia());
                    }                                
                    if (escolha == 3) {
                        System.out.println(o.imprimirListAdjacencia());
                    }                                             
                }while(escolha != 4);                                                      
            }

            if(escolha == 6){                            
                do{
                    System.out.println("=========================");
                    System.out.println("1 - Ordem do Grafo");
                    System.out.println("2 - Grau de Emissão");
                    System.out.println("3 - Grau de Recepção");                    
                    System.out.println("4 - Grafo conexo ou desconexo");
                    System.out.println("5 - Vértices Fontes");
                    System.out.println("6 - Vértices Sumidouro");
                    System.out.println("7 - Fecho Transitivo Direto e Inverso");
                    System.out.println("11 - Sair");
                    System.out.println("=========================");
                    escolha = in.nextInt();
                    in.nextLine();

                    if(escolha == 1){
                        System.out.println(o.retornarOrdemGrafo());
                    }                                
                    if (escolha == 2) {
                        System.out.println(o.imprimriGrauEmissao());
                    }
                    if (escolha == 3) {
                        System.out.println(o.imprimirrGrauRecepcao());
                    }
                    if (escolha == 4) {
                        System.out.println();
                        System.out.println(o.verificarConexo());
                    }
                    if (escolha == 5) {
                        System.out.println(o.imprimirFonte());
                    }
                    if (escolha == 6) {
                        System.out.println(o.imprimirSumidouro());
                    }
                    if (escolha == 7){
                        System.out.println(o.imprimirTransitivo());
                    }                          
                }while(escolha != 11);
            }

            if (escolha == 7){
                do{
                    System.out.println("=========================");
                    System.out.println("1 - Caminho");
                    System.out.println("2 - Camiho Mínimo (Dijkstra)");
                    System.out.println("3 - Sair");
                    System.out.println("=========================");
                    escolha = in.nextInt();
                    in.nextLine();

                    if(escolha == 1){
                        System.out.println("=========================");
                        System.out.println("Informe o nome do primeiro vértice: ");
                        System.out.println("=========================");
                        String v1 = in.nextLine();
                        System.out.println("=========================");
                        System.out.println("Informe o nome do segundo vértice: ");
                        System.out.println("=========================");
                        String v2 = in.nextLine();
                        System.out.println(o.imprimirCaminho(v1, v2));

                    }

                    if(escolha == 2){
                        System.out.println(o.imprimirDijkstra());
                    }
                             
                }while(escolha !=3);
            


            }
        }while(escolha != 10);            



    }  

    public static void apresentarMenuNaoOrientado (GrafoNaoOrientado no) throws IOException{
        int escolha = 0;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("=========================");
            System.out.println("1 - Adicionar Vértice");    
            System.out.println("2 - Adicionar Aresta"); 
            System.out.println("3 - Remover Vértice"); 
            System.out.println("4 - Remover Aresta"); 
            System.out.println("5 - Imprimir Grafo"); 
            System.out.println("6 - Informações do Grafo");
            System.out.println("7 - Algoritmos");
            System.out.println("10 - Sair");
            System.out.println("=========================");
            escolha = in.nextInt();
            in.nextLine();

            if(escolha == 1){
                System.out.println("=========================");
                System.out.println("Informe o nome do vértice a ser adicionado");
                System.out.println("=========================");
                String nomev = in.nextLine();
                no.adicionarVertice(nomev);

            }

            if (escolha == 2){
                System.out.println("=========================");
                System.out.println("Informe o vertice de origem da aresta");
                System.out.println("=========================");
                String nomeao = in.nextLine();    
                System.out.println("=========================");
                System.out.println("Informe o vertice de destino da aresta");
                System.out.println("=========================");
                String nomead = in.nextLine();                            

                if(no.isValorado() == true){
                    System.out.println("=========================");
                    System.out.println("Informe o valor da aresta");
                    System.out.println("=========================");
                    int varesta = in.nextInt();
                    in.nextLine();
                    no.adicionarAresta(nomeao, nomead, varesta);
                }

                else{                                
                    no.adicionarAresta(nomeao, nomead, 0);                                
                }
            }

            if(escolha == 3){
                System.out.println("=========================");
                System.out.println("Informe o nome do vértice a ser removido ");
                System.out.println("=========================");
                String nomev = in.nextLine();
                no.removerVertice(nomev);


            }

            if (escolha == 4){
                System.out.println("=========================");
                System.out.println("Informe o vértice de origem da aresta");
                System.out.println("=========================");
                String nomeao = in.nextLine();         
                System.out.println("=========================");
                System.out.println("Informe o vértice de destino da aresta");
                System.out.println("=========================");
                String nomead = in.nextLine();   
                no.removerAresta(nomeao, nomead);                            

            }

            if (escolha == 5){
                do{
                    System.out.println("=========================");
                    System.out.println("Escolha uma das formas de impressão");
                    System.out.println("1 - Matriz Adjacência");
                    System.out.println("2 - Matriz Incidência");
                    System.out.println("3 - Lista Adjacência");
                    System.out.println("4 - Sair");
                    System.out.println("=========================");

                    escolha = in.nextInt();
                    in.nextLine();

                    if (escolha == 1) {
                        System.out.println(no.imprimirMatrizAdjacencia());
                    }
                    if (escolha == 2) {
                        System.out.println(no.imprimirMatrizIncidencia());
                    }                                
                    if (escolha == 3) {
                        System.out.println(no.imprimirListAdjacencia());
                    }                                             
                }while(escolha != 4);                                                      
            }

            if(escolha == 6){                            
                do{
                    System.out.println("=========================");
                    System.out.println("1 - Ordem do Grafo");
                    System.out.println("2 - Grau dos Vértices");
                    System.out.println("3 - Grafo Simples");
                    System.out.println("4 - Grafo conexo ou desconexo");
                    System.out.println("5 - Grafo Regular");
                    System.out.println("6 - Grafo Completo");
                    System.out.println("7 - Fecho Transitivo Direto e Inverso");
                    System.out.println("11 - Sair");
                    System.out.println("=========================");
                    escolha = in.nextInt();
                    in.nextLine();

                    if(escolha == 1){
                        System.out.println(no.retornarOrdemGrafo());
                    }                                
                    if (escolha == 2) {
                        System.out.println(no.retornarMapGrauVertices());
                    }
                    if (escolha == 3) {
                        System.out.println(no.imprimirSimples());
                    }
                    if (escolha == 4) {
                        System.out.println();
                        System.out.println(no.verificarConexo());
                    }
                    if (escolha == 5) {
                        System.out.println(no.verificarRegular());
                    }
                    if (escolha == 6) {
                        System.out.println(no.verificarCompleto());
                    }
                    if (escolha == 7) {
                        System.out.println(no.imprimirTransitivo());
                    }                                
                }while(escolha != 11);
            }

            if (escolha == 7){
                do{
                    System.out.println("=========================");
                    System.out.println("1 - Caminho");
                    System.out.println("2 - Camiho Mínimo (Dijkstra)");
                    System.out.println("3 - Sair");
                    System.out.println("=========================");
                    escolha = in.nextInt();
                    in.nextLine();

                    if(escolha == 1){
                        System.out.println("=========================");
                        System.out.println("Informe o nome do primeiro vértice: ");
                        System.out.println("=========================");
                        String v1 = in.nextLine();
                        System.out.println("=========================");
                        System.out.println("Informe o nome do segundo vértice: ");
                        System.out.println("=========================");
                        String v2 = in.nextLine();
                        System.out.println(no.imprimirCaminho(v1, v2));

                    }

                    if(escolha == 2){
                        System.out.println(no.imprimirDijkstra());
                    }
                             
                }while(escolha !=3);
            


            }
        }while(escolha != 10);            



    }  
    
}
