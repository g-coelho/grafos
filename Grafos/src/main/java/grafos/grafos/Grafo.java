/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author gabri
 */
public class Grafo {
    private ArrayList<Vertice> vertices;
    private HashMap<String, Vertice> verticesMap;
    private ArrayList<Aresta> arestas;
    private HashMap<String, Aresta> arestasMap;
    private boolean orientado;
    private boolean valorado;
    

    public Grafo(boolean orientado, boolean valorado) {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.verticesMap = new HashMap<>();
        this.arestasMap = new HashMap<>();
        this.orientado = orientado;
        this.valorado = valorado;
    }

    public void adicionarVertice(String nome){
        Vertice v = new Vertice(nome);        
        vertices.add(v);
        verticesMap.put(v.getNome(), v);
    }
    
    public void removerVertice(String nome){         
        arestasMap.entrySet().removeIf(chave -> chave.getValue().getOrigem().getNome().equals(nome));
        arestasMap.entrySet().removeIf(chave -> chave.getValue().getDestino().getNome().equals(nome));
        vertices.remove(verticesMap.get(nome));
        verticesMap.remove(nome);   
    }
    
    public void adicionarAresta(String origem, String destino, int valor){
        Aresta a = new Aresta(verticesMap.get(origem), verticesMap.get(destino));        
        arestas.add(a);     
        arestasMap.put(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome(), a);
        alterarValorAresta(origem, destino, valor);
    }
    
    public void removerAresta(String origem, String destino){
        arestasMap.remove(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome());
        arestas.remove(arestasMap.get(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome()));
    }
    
    public void alterarValorAresta(String origem, String destino, int valor){
        arestasMap.get(origem + destino).setValor(valor);
        arestas.remove(arestasMap.get(origem + destino));
        arestas.add(arestasMap.get(origem + destino));
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
    
    
    
    
    
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        for (Vertice v : vertices){
            s.append(v.getNome() + ", ");
        }
        
        s.append("\n");
        
        for(String nome: arestasMap.keySet()){
            s.append(nome + ", ");
        }
        return s.toString();
    }
    
    
    
    
    
    
    
    
    
}
