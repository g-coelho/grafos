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
    private boolean conexo;
    

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
    
    public String retornarGrauVertices(){
        HashMap<String, Integer> mapGrau = mapearVertices();        
        StringBuilder s = new StringBuilder();
        
        if (orientado == false) {
            for (Aresta a: arestas) {
                mapGrau.replace(a.getOrigem().getNome(), mapGrau.get(a.getOrigem().getNome())+1);
                mapGrau.replace(a.getDestino().getNome(), mapGrau.get(a.getDestino().getNome())+1);
            }
        }
        else{
            return "O grafo é orientado, portanto solicite grau de emissão ou recepção.";
        }
        
        for (String vertice: mapGrau.keySet()){
            s.append(vertice + ": " + mapGrau.get(vertice));
            s.append("\n");
        }        
        return s.toString();
    }
    
    public String retornarGrauEmissao(){
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
    
    public String retornarGrauRecepcao(){
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

    public String verificarSimples(){                
        ArrayList<String> parAresta = new ArrayList<>();
        
        for (Aresta a : arestas){
            if (a.getDestino() == a.getOrigem()) {
                return "O grafo não é simples pois possui laço.";
            }
        }        
        for (Aresta a : arestas) {            
            if (parAresta.contains(a.getOrigem().getNome() + a.getDestino().getNome()) || parAresta.contains(a.getDestino().getNome() + a.getOrigem().getNome())) {
                return "O grafo não é simples pois possui arestas múltiplas";
            }            
            parAresta.add(a.getOrigem().getNome() + a.getDestino().getNome());
        }
        return "O grafo é simples.";      
    }
    
    public String verificarConexo(){
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
            this.conexo = true;
            return "O grafo é conexo.";
        }else{
            this.conexo = false;
            return "O grafo é desconexo.";
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
    
    public String verificarFonte() {
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

    public String verificarSumidouro() {
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
