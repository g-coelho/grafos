/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author gabri
 */
public class Grafo {
    private ArrayList<Vertice> vertices;
    private HashMap<String, Vertice> verticesMap;
    private ArrayList<Aresta> arestas;
    private boolean orientado;
    private boolean valorado;
    

    public Grafo(boolean orientado, boolean valorado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.verticesMap = new HashMap<>();
        this.orientado = orientado;
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
        Aresta a = new Aresta(verticesMap.get(origem), verticesMap.get(destino));        
        arestas.add(a);     
        alterarValorAresta(origem, destino, valor);
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
            }
        }

        
    }
    
    public int [][] gerarMatrixAdjacencia(){        
        int [][] matrAdj;
        matrAdj = new int[vertices.size()][vertices.size()];
        

        for(Aresta a: arestas){
            Vertice o = a.getOrigem();
            Vertice d = a.getDestino(); 
            
            if (valorado == true){ 
                matrAdj[vertices.indexOf(o)][vertices.indexOf(d)] = (a.getValor() == 0)? 1 : a.getValor();            
                if (orientado == false) {                
                    matrAdj[vertices.indexOf(d)][vertices.indexOf(o)] = (a.getValor() == 0)? 1 : a.getValor(); 
                }                               
            }           
            else{ 
                matrAdj[vertices.indexOf(o)][vertices.indexOf(d)] = 1;            
                if (orientado == false) {                
                    matrAdj[vertices.indexOf(d)][vertices.indexOf(o)] = 1; 
                }                
            }
        }
        return  matrAdj;        
    }     
    
    public LinkedList<Integer>[] gerarListaAdjacencia(){
        
        LinkedList<Integer> adjList [];
        adjList = new LinkedList[vertices.size()];        
        for (int i = 0; i < vertices.size(); i++) {
                adjList[i] = new LinkedList<>();
            }
                
        for (Aresta a : arestas){            
            if(adjList[vertices.indexOf(a.getOrigem())].contains(vertices.indexOf(a.getDestino())) == false)
                adjList[vertices.indexOf(a.getOrigem())].addFirst(vertices.indexOf(a.getDestino()));            
            
            if (orientado == false){
                if (adjList[vertices.indexOf(a.getDestino())].contains(vertices.indexOf(a.getOrigem())) == false)
                    adjList[vertices.indexOf(a.getDestino())].addFirst(vertices.indexOf(a.getOrigem()));  
                    
            }

        }        
        return adjList;        
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
    
    public String imprimirGrauVertices(){
        StringBuilder s = new StringBuilder();        
        if (orientado == false) {   
            HashMap<String, Integer> mapGrau = retornarMapGrauVertices();
            for (String vertice: mapGrau.keySet()){
                s.append(vertice).append(": ").append(mapGrau.get(vertice));
                s.append("\n");
            }        
            return s.toString();              
        }
        else{
            return "O grafo é orientado, portanto solicite grau de emissão ou recepção.";
        }
    }
        
    
    public String imprimriGrauEmissao(){
        HashMap<String, Integer> mapEmissao = mapearVertices();
        StringBuilder s = new StringBuilder();     
        
        if (orientado == true) {
            for (Aresta a: arestas){
                mapEmissao.replace(a.getOrigem().getNome(), mapEmissao.get(a.getOrigem().getNome())+1);
            }       
        }
        
        else{
            return "O grafo não é orientado, portanto solicite apenas o grau dos vértices.";
        }
        for (String vertice: mapEmissao.keySet()){
            s.append(vertice + ": " + mapEmissao.get(vertice));
            s.append("\n");
        }        
        return s.toString();
    }
    
    public String imprimirrGrauRecepcao(){
        HashMap<String, Integer> mapRecepcao = mapearVertices();
        StringBuilder s = new StringBuilder();     
        
        if (orientado == true) {
            for (Aresta a: arestas){
                mapRecepcao.replace(a.getDestino().getNome(), mapRecepcao.get(a.getDestino().getNome())+1);
            }       
        }
        
        else{
            return "O grafo não é orientado, portanto solicite apenas o grau dos vértices.";
        }
        for (String vertice: mapRecepcao.keySet()){
            s.append(vertice + ": " + mapRecepcao.get(vertice));
            s.append("\n");
        }        
        return s.toString();
    }    

    public String imprimirSimples(){                
        ArrayList<String> parAresta = new ArrayList<>();
        

        if (verificarLaço() == true) {
            return "O grafo não é simples pois possui laço.";
        }
    
        for (Aresta a : arestas) {            
            if (parAresta.contains(a.getOrigem().getNome() + a.getDestino().getNome()) || parAresta.contains(a.getDestino().getNome() + a.getOrigem().getNome())) {
                return "O grafo não é simples pois possui arestas múltiplas";
            }            
            parAresta.add(a.getOrigem().getNome() + a.getDestino().getNome());
        }
        return "O grafo é simples.";      
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
    
    public String imprimirFonte() {
        ArrayList<String> verticesDestino = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        
        s.append("São fonte os seguintes vértices: ");
      
        for(Aresta a: arestas){
            verticesDestino.add(a.getDestino().getNome());
        }
        
        for(Vertice v: vertices){
            if(verticesDestino.contains(v.getNome())== false){                
                s.append(v.getNome());
                s.append(", ");
            }
        }
        return s.toString();        
    }

    public String imprimirSumidouro() {
        ArrayList<String> verticesOrigem = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        
        s.append("São sumidouro os seguintes vértices: ");
      
        for(Aresta a: arestas){
            verticesOrigem.add(a.getOrigem().getNome());
        }
        
        for(Vertice v: vertices){
            if(verticesOrigem.contains(v.getNome())== false){                
                s.append(v.getNome());
                s.append(", ");
            }
        }
        return s.toString();         

    }    
    
    public boolean verificarRegular(){
        HashMap<String, Integer> mapGrau = retornarMapGrauVertices();
        int grau = mapGrau.get(vertices.get(0).getNome());
        for (String v : mapGrau.keySet()){
            if (mapGrau.get(v) != grau){
                return false;
            }
        }
        return true;
    }
    
    public String imprimirRegular(){
        if (orientado == true) {
            return "O grafo é orientado então essa verificação não pode ser feita.";            
        }
        
        if (verificarRegular() == true) {
            return "O grafo é regular.";
        }
        
        else{
            return "O grafo não é regular";
        }
    }
    
    public boolean verificarCompleto(){
        int n = vertices.size();
        int kn = (n*n - n)/2;
        
        if (kn != arestas.size()){
            return false;
        }
        return true;
    }    
    
    public String imprimirCompleto(){
        if (orientado) {
            return "O grafo é orientado então essa verificação não pode ser feita";
        }
        if (verificarCompleto() == true) {
            return "O grafo é completo";
        }
        else{
            return "O grafo não é completo.";
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
    
    public int [][] gerarMatrizCaminho(){
        int g[][]= new int[vertices.size()][vertices.size()];
        for(Aresta a: arestas){
            Vertice o = a.getOrigem();
            Vertice d = a.getDestino();             
            g[vertices.indexOf(o)][vertices.indexOf(d)] = 1;            
            if (orientado == false) {                
                g[vertices.indexOf(d)][vertices.indexOf(o)] = 1; 
            }
        }        

        for(Vertice k: vertices)
        {
            for(Vertice i: vertices)
            {
                for(Vertice j: vertices)
                    g[vertices.indexOf(i)][vertices.indexOf(j)] = g[vertices.indexOf(i)][vertices.indexOf(j)] | ((g[vertices.indexOf(i)][vertices.indexOf(k)] != 0 &&
                              g[vertices.indexOf(k)][vertices.indexOf(j)] != 0) ? 1 : 0);
            }
        }    
        
        return  g; 
    }     
    
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
    
    
    
    
    
    
    
    
}
