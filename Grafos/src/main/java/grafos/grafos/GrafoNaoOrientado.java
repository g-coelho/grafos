/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

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
    
    
}
