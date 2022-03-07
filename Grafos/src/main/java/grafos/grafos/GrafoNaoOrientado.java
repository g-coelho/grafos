/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
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
    
    
    public int [] gerarPrim(){                
        int V = getVertices().size();
        int[][] grafo = gerarMatrixAdjacencia();
        int [][] matrAdjPrim = new int[getVertices().size()][getVertices().size()];   
        StringBuilder s = new StringBuilder();
        
        
        int parent[] = new int[V];
        int key[] = new int[V];
 
        boolean mstSet[] = new boolean[V];
 
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0; 
        parent[0] = -1;
 
        for (int count = 0; count < V - 1; count++) {
            int u = encontrarMenorDistancia(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < V; v++)
                if (grafo[u][v] != 0 && mstSet[v] == false && grafo[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = grafo[u][v];
                }
        }
        return parent;   
    }        
    
    public String imprimirPrim(){
        int V = getVertices().size();   
        int [][] grafo = gerarMatrixAdjacencia();
        int [] parent = gerarPrim();
        StringBuilder s = new StringBuilder();        
        
        if(verificarConexo() == false){
            return "Não é possível gerar a árvore geradora mínima porque o grafo é desconexo.";
        }
        
        if(verificarLaço() == true){
            return "Não é possível gerar a árvore geradora mínima porque o grafo possui um laço.";
        }        
        
        s.append("Aresta \t| Peso");
        s.append("\n");

        for (int i = 1; i < V; i++){
            String o = getVertices().get(parent[i]).getNome();
            String d = getVertices().get(i).getNome();
            
            
            s.append(o).append(" - ").append(d).append("\t  ").append(grafo[i][parent[i]]);
            s.append("\n");
        }                    

        return s.toString();
        
    }     
    
    public int [][] gerarPrimMatrizAdj(){        
        int [] parent = gerarPrim();
        int[][] grafo = gerarMatrixAdjacencia();
        int [][] matrAdjPrim = new int [getVertices().size()][getVertices().size()];
        
        for(int i = 1; i < getVertices().size(); i++){
            matrAdjPrim[getVertices().indexOf(getVertices().get(parent[i]))][getVertices().indexOf(getVertices().get(i))] = grafo[i][parent[i]];
            matrAdjPrim[getVertices().indexOf(getVertices().get(i))][getVertices().indexOf(getVertices().get(parent[i]))] = grafo[i][parent[i]];         
        }                
        return  matrAdjPrim;
    };

    public String imprimirPrimMatrizAdj(){

        int r[][] = gerarPrimMatrizAdj();
        StringBuilder s = new StringBuilder();
        
        if(verificarConexo() == false){
            return "Não é possível gerar a árvore geradora mínima porque o grafo é desconexo.";
        }
        
        if(verificarLaço() == true){
            return "Não é possível gerar a árvore geradora mínima porque o grafo possui um laço.";
        }                
        
        for (Vertice v : getVertices())
        {
          s.append(v.getNome()).append(": ");
          
          for (int i = 0; i < getVertices().size(); i++) {                
            s.append(r[getVertices().indexOf(v)][i]).append(" ");
          }
          s.append("\n");                        
        }
        return s.toString(); 
        
    }
   
   public void acoplarArestasPesoMinimo(){
       int [][] grafoPrim = gerarPrimMatrizAdj();
       int [] grauVertices = new int [getVertices().size()];
       
       for (int i = 0; i < grafoPrim.length; i++) {
           for (int j = 0; j < grafoPrim[i].length; j++) {
               grauVertices[i] = grauVertices[i] + grafoPrim[i][j];
           }
       }
       
       System.out.println(Arrays.toString(grauVertices));
       
       
   }    
    
    
    
}
