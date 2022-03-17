/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author gabri
 */
public abstract class Grafo {
    private ArrayList<Vertice> vertices;
    private HashMap<String, Vertice> verticesMap;
    private ArrayList<Aresta> arestas;
    private boolean valorado;
    

    public Grafo(boolean valorado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.verticesMap = new HashMap<>();
        this.valorado = valorado;
    }

    public void adicionarVertice(String nome){
        Vertice v = new Vertice(nome);        
        vertices.add(v);
        verticesMap.put(v.getNome(), v);
    }
    
    public void removerVertice(String nome){         
        Iterator<Aresta> itrA = arestas.iterator();        
        while (itrA.hasNext()){
            Aresta a = itrA.next();            
            if(a.getOrigem().getNome().equals(nome)|| a.getDestino().getNome().equals(nome)){               
                itrA.remove();
            }               
        }
        
        vertices.remove(verticesMap.get(nome));
        verticesMap.remove(nome);   
    }
    
    public void adicionarAresta(String origem, String destino, int valor){
        Aresta a = new Aresta(verticesMap.get(origem), verticesMap.get(destino), valor);        
        arestas.add(a);     
    }
    
    public void removerAresta(String origem, String destino){
        Iterator<Aresta> itrA = arestas.iterator();        
        while (itrA.hasNext()){
            Aresta a = itrA.next(); 
            if (a.getOrigem().getNome().equals(origem) && a.getDestino().getNome().equals(destino)) {
                itrA.remove();       
            }
        }
    }
    
    public void alterarValorAresta(String origem, String destino, int valor){
        Iterator<Aresta> itrA = arestas.iterator();        
        while (itrA.hasNext()){
            Aresta a = itrA.next(); 
            if (a.getOrigem().getNome().equals(origem) && a.getDestino().getNome().equals(destino)) {
                itrA.remove();
            }
        }
        
        adicionarAresta(origem, destino, valor);
    }
    
    public abstract void gerarAleatorio();
    
    public abstract int [][] gerarMatrixAdjacencia();    
    
    public String imprimirMatrizAdjacencia(){
        
        int r[][] = gerarMatrixAdjacencia();
        StringBuilder s = new StringBuilder();
        
        for (Vertice v : vertices)
        {
          s.append(v.getNome()).append(": ");
          
          for (int i = 0; i < vertices.size(); i++) {                
            s.append(r[vertices.indexOf(v)][i]).append(" ");
          }
          s.append("\n");                        
        }
        return s.toString(); 

    }        
    
    public abstract LinkedList<Integer>[] gerarListaAdjacencia();
    
    public String imprimirListAdjacencia(){        
        LinkedList<Integer> list [] = gerarListaAdjacencia();
        StringBuilder s = new StringBuilder();        
        
        for (int i = 0; i < vertices.size() ; i++) {
            if(list[i].size()>0) {                
                s.append(vertices.get(i).getNome()).append(": ");                
                for (int j = 0; j < list[i].size(); j++) {
                    s.append(vertices.get(list[i].get(j)).getNome()).append(" ");
                }
                s.append("\n");
            }
        }        
        
        return s.toString();
    }    
    
    public int retornarOrdemGrafo(){      
        return vertices.size();
    }
    
    public HashMap<String, Integer> mapearVertices(){
        HashMap<String, Integer> MapVertices = new HashMap<>();        
        for (Vertice v: vertices) {
            MapVertices.put(v.getNome(), 0);                
        }
        return MapVertices;
    }    
    
    public HashMap<String, Integer> retornarMapGrauVertices(){
        HashMap<String, Integer> mapGrau = mapearVertices();               
        for (Aresta a: arestas) {
            mapGrau.replace(a.getOrigem().getNome(), mapGrau.get(a.getOrigem().getNome())+1);
            mapGrau.replace(a.getDestino().getNome(), mapGrau.get(a.getDestino().getNome())+1);
        }
        return mapGrau;        
    }
    
    public boolean verificarLaço(){
        for (Aresta a : arestas){
            if (a.getDestino() == a.getOrigem()) {
                return true;
            } 
        }
        return false;
    }    
    
    public boolean verificarConexo(){
        int v = vertices.size();
        LinkedList<Integer> listaAdjacencia [] = gerarListaAdjacencia(); 
        
        boolean[] visitado = new boolean[v];
        
        DFS(0, listaAdjacencia, visitado);
        
        int count = 0;
        for (int i = 0; i < visitado.length ; i++) {
            if(visitado[i])
                count++;
        }
        if(v == count){
            return true;
        }else{
            return false;
        }                  
    }

    public String imprimirConexo(){
        if (verificarConexo() == true) {
            return "O grafo é conexo";
        }
        else{
            return "O grafo é desconexo";    
        }
    }    
    
    public void DFS(int inicio, LinkedList<Integer> listaAdjacencia [], boolean[] visitado){
        visitado[inicio] = true;
        for (int i = 0; i < listaAdjacencia[inicio].size() ; i++) {
            int vizinho = listaAdjacencia[inicio].get(i);
            if(visitado[vizinho] == false){
                DFS(vizinho, listaAdjacencia, visitado);
            }        
        }    
    }        

    public int[][] gerarTransitivo (){
        int reach [][] = gerarMatrixAdjacencia();   
        int  i, j, k;
        int V = vertices.size();
        for (k = 0; k < V; k++)
        {
            for (i = 0; i < V; i++)
            {
                for (j = 0; j < V; j++)
                {
                    reach[i][j] = (reach[i][j]!=0) ||
                             ((reach[i][k]!=0) && (reach[k][j]!=0))?1:0;
                }
            }
        }
        return reach;       
    }
    
    public String imprimirTransitivo(){
        
        int t[][] = gerarTransitivo();
        StringBuilder s = new StringBuilder();

        for (Vertice i : vertices)
        {
            s.append(i.getNome()).append(": ");            
            for (Vertice j : vertices) {
                if (vertices.indexOf(i) == vertices.indexOf(j))
                  s.append("1 ");
                else
                  s.append(t[vertices.indexOf(i)][vertices.indexOf(j)]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }        
    
    public abstract int [][] gerarMatrizCaminho();
    
    public boolean verificarCaminho(Vertice A, Vertice B){
        int g[][] = gerarMatrizCaminho();
        return g[vertices.indexOf(A)][vertices.indexOf(B)] == 1;
    };
    
    public String imprimirCaminho(String v1, String v2){    
        Vertice A = verticesMap.get(v1);
        Vertice B = verticesMap.get(v2);

        if (verificarCaminho(A, B))
            return "Há caminho entre os dois vértices.";
        else {
            return "Não há um caminho entre os dois vértices.";  
        }
    }    
    
    public String imprimirMatrizCaminho(){
        int g[][] = gerarMatrizCaminho();
        StringBuilder s = new StringBuilder();
        int i = 0;
        for (int[] row : g) {                    
            s.append(vertices.get(i).getNome()).append(": ");
            i++;
            
            for (int val : row) {
                s.append(val).append(" ");                
            }
            s.append("\n");
        }        
        return s.toString();
    }     
    
    public int[] gerarDijkstra(){
        
        int V = vertices.size();
        int grafo[][]= gerarMatrixAdjacencia();  
        int src = 0;        
        int dist[] = new int[V]; 
        boolean sptSet[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }


        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = encontrarMenorDistancia(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && grafo[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + grafo[u][v] < dist[v])
                    dist[v] = dist[u] + grafo[u][v];
        }
        return dist;        
    }     
    
    int encontrarMenorDistancia(int dist[], boolean sptSet[]){
        int V = vertices.size();        
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }        
    
    public String imprimirDijkstra()    {
        int dist[] = gerarDijkstra();         
        StringBuilder s = new StringBuilder();
        int V = vertices.size();
        s.append("Vértice | Distância do vértice fonte");
        s.append("\n");
        for (int i = 0; i < V; i++){
            s.append(vertices.get(i).getNome()).append(": \t\t").append(dist[i]);
            s.append("\n");
        }        
        return  s.toString();
    }      

    public String imprimirMatrizIncidencia(){
        int[][] matrizInc = new int[vertices.size()][arestas.size()];
        StringBuilder s = new StringBuilder();
        int i = 0;
        
        for (Aresta a : arestas){
            if (a.getOrigem() == a.getDestino()) {
                matrizInc[vertices.indexOf(a.getDestino())][arestas.indexOf(a)] = 2;
            }
            else{            
                matrizInc[vertices.indexOf(a.getDestino())][arestas.indexOf(a)] = 1;
                matrizInc[vertices.indexOf(a.getOrigem())][arestas.indexOf(a)] = 1;
            }
        }

        for (int[] row : matrizInc) {                    
            s.append(vertices.get(i).getNome() + ": ");
            i++;
            
            for (int val : row) {
                s.append(val + " ");                
            }
            s.append("\n");
        }        
        return s.toString(); 
    }       

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public boolean isValorado() {
        return valorado;
    }

    public void setValorado(boolean valorado) {
        this.valorado = valorado;
    }
 

   
    
    
    
    
    
    
    
    
}
