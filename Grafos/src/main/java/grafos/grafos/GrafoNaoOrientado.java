/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author gabri
 */
public class GrafoNaoOrientado extends Grafo {

    public GrafoNaoOrientado(boolean valorado) {
        super(valorado);
    }
    
    @Override
    public void gerarAleatorio() {
        String [] verticesExemplo = {"A","B", "C", "D", "E"};
        String [] arestasExemploSimples = {"AB","AC","BC","BD","CD","CE"};
        for(String v: verticesExemplo){
            adicionarVertice(v);
        }
        for (String a: arestasExemploSimples) {
            adicionarAresta(Character.toString(a.charAt(0)), Character.toString(a.charAt(1)), 0);
        }        
        
    }    
    
    @Override
    public int [][] gerarMatrixAdjacencia(){        
        int [][] matrAdj;
        matrAdj = new int[getVertices().size()][getVertices().size()];        

        for(Aresta a: getArestas()){
            Vertice o = a.getOrigem();
            Vertice d = a.getDestino(); 
            
            if (isValorado() == true){ 
                matrAdj[getVertices().indexOf(o)][getVertices().indexOf(d)] = (a.getValor() == 0)? 1 : a.getValor();                        
                matrAdj[getVertices().indexOf(d)][getVertices().indexOf(o)] = (a.getValor() == 0)? 1 : a.getValor(); 
                                               
            }           
            else{ 
                matrAdj[getVertices().indexOf(o)][getVertices().indexOf(d)] = 1;            
              
                matrAdj[getVertices().indexOf(d)][getVertices().indexOf(o)] = 1; 
          
            }
        }
        return  matrAdj;    
        
    }        
    
    @Override
    public LinkedList<Integer>[] gerarListaAdjacencia(){
        
        LinkedList<Integer> adjList [];
        adjList = new LinkedList[getVertices().size()];        
        for (int i = 0; i < getVertices().size(); i++) {
                adjList[i] = new LinkedList<>();
            }
                
        for (Aresta a : getArestas()){            
            if(adjList[getVertices().indexOf(a.getOrigem())].contains(getVertices().indexOf(a.getDestino())) == false){
                adjList[getVertices().indexOf(a.getOrigem())].addFirst(getVertices().indexOf(a.getDestino()));  
            }
        
            if (adjList[getVertices().indexOf(a.getDestino())].contains(getVertices().indexOf(a.getOrigem())) == false){
                adjList[getVertices().indexOf(a.getDestino())].addFirst(getVertices().indexOf(a.getOrigem()));  
            }
                    
            

        }        
        return adjList;        
    }         
    
    public String imprimirGrauVertices(){
        StringBuilder s = new StringBuilder();        
  
            HashMap<String, Integer> mapGrau = retornarMapGrauVertices();
            for (String vertice: mapGrau.keySet()){
                s.append(vertice).append(": ").append(mapGrau.get(vertice));
                s.append("\n");
            }        
            return s.toString();              
    }    
    
    public String imprimirSimples(){                
        ArrayList<String> parAresta = new ArrayList<>();
        if (verificarLaço() == true) {
            return "O grafo não é simples pois possui laço.";
        }
    
        for (Aresta a : getArestas()) {            
            if (parAresta.contains(a.getOrigem().getNome() + a.getDestino().getNome()) || parAresta.contains(a.getDestino().getNome() + a.getOrigem().getNome())) {
                return "O grafo não é simples pois possui arestas múltiplas";
            }            
            parAresta.add(a.getOrigem().getNome() + a.getDestino().getNome());
        }
        return "O grafo é simples.";      
    }    

    public boolean verificarRegular(){
        HashMap<String, Integer> mapGrau = retornarMapGrauVertices();
        int grau = mapGrau.get(getVertices().get(0).getNome());
        for (String v : mapGrau.keySet()){
            if (mapGrau.get(v) != grau){
                return false;
            }
        }
        return true;
    }
    
    public String imprimirRegular(){        
        if (verificarRegular() == true) {
            return "O grafo é regular.";
        }
        
        else{
            return "O grafo não é regular";
        }
    }    
    
    public boolean verificarCompleto(){
        int n = getVertices().size();
        int kn = (n*n - n)/2;
        
        if (kn != getArestas().size()){
            return false;
        }
        return true;
    }    
    
    public String imprimirCompleto(){
        if (verificarCompleto() == true) {
            return "O grafo é completo";
        }
        else{
            return "O grafo não é completo.";
        }        
    }    
    
    @Override
    public int [][] gerarMatrizCaminho(){
        int g[][]= new int[getVertices().size()][getVertices().size()];
        for(Aresta a: getArestas()){
            Vertice o = a.getOrigem();
            Vertice d = a.getDestino();             
            g[getVertices().indexOf(o)][getVertices().indexOf(d)] = 1;           
               
            g[getVertices().indexOf(d)][getVertices().indexOf(o)] = 1; 
            
        }        

        for(Vertice k: getVertices())
        {
            for(Vertice i: getVertices())
            {
                for(Vertice j: getVertices())
                    g[getVertices().indexOf(i)][getVertices().indexOf(j)] = g[getVertices().indexOf(i)][getVertices().indexOf(j)] | ((g[getVertices().indexOf(i)][getVertices().indexOf(k)] != 0 &&
                              g[getVertices().indexOf(k)][getVertices().indexOf(j)] != 0) ? 1 : 0);
            }
        }    
        
        return  g; 
    }         


    
    

       
       
    
    
    
}
