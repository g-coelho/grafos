/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.HashMap;
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
    
}
