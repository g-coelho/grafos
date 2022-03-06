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
public class GrafoOrientado extends Grafo {

    public GrafoOrientado(boolean valorado) {
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
            }           
            else{ 
                matrAdj[getVertices().indexOf(o)][getVertices().indexOf(d)] = 1;                     
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
            if(adjList[getVertices().indexOf(a.getOrigem())].contains(getVertices().indexOf(a.getDestino())) == false)
                adjList[getVertices().indexOf(a.getOrigem())].addFirst(getVertices().indexOf(a.getDestino()));            
            

        }        
        return adjList;        
    }      

    public String imprimriGrauEmissao(){
        HashMap<String, Integer> mapEmissao = mapearVertices();
        StringBuilder s = new StringBuilder();     
        for (Aresta a: getArestas()){
            mapEmissao.replace(a.getOrigem().getNome(), mapEmissao.get(a.getOrigem().getNome())+1);
        }       
        
        for (String vertice: mapEmissao.keySet()){
            s.append(vertice).append(": ").append(mapEmissao.get(vertice));
            s.append("\n");
        }        
        return s.toString();
    }
        
    public String imprimirrGrauRecepcao(){
        HashMap<String, Integer> mapRecepcao = mapearVertices();
        StringBuilder s = new StringBuilder();     
        for (Aresta a: getArestas()){
            mapRecepcao.replace(a.getDestino().getNome(), mapRecepcao.get(a.getDestino().getNome())+1);
        }       

        for (String vertice: mapRecepcao.keySet()){
            s.append(vertice).append(": ").append(mapRecepcao.get(vertice));
            s.append("\n");
        }        
        return s.toString();
    }        
    
    public String imprimirFonte() {
        ArrayList<String> verticesDestino = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        
        s.append("São fonte os seguintes vértices: ");
      
        for(Aresta a: getArestas()){
            verticesDestino.add(a.getDestino().getNome());
        }
        
        for(Vertice v: getVertices()){
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
      
        for(Aresta a: getArestas()){
            verticesOrigem.add(a.getOrigem().getNome());
        }
        
        for(Vertice v: getVertices()){
            if(verticesOrigem.contains(v.getNome())== false){                
                s.append(v.getNome());
                s.append(", ");
            }
        }
        return s.toString();         

    }   
    
    public int [][] gerarMatrizCaminho(){
        int g[][]= new int[getVertices().size()][getVertices().size()];
        for(Aresta a: getArestas()){
            Vertice o = a.getOrigem();
            Vertice d = a.getDestino();             
            g[getVertices().indexOf(o)][getVertices().indexOf(d)] = 1;            
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
