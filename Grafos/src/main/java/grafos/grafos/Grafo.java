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

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.verticesMap = new HashMap<>();
        this.arestasMap = new HashMap<>();
    }

    public void adicionarVertice(String nome){
        Vertice v = new Vertice(nome);        
        vertices.add(v);
        verticesMap.put(v.getNome(), v);
    }
    
    public void removerVertice(String nome){         
        arestasMap.entrySet().removeIf(chave -> chave.getValue().getOrigem().getNome().equals(nome));
        arestasMap.entrySet().removeIf(chave -> chave.getValue().getOrigem().getNome().equals(nome));
        vertices.remove(verticesMap.get(nome));
        verticesMap.remove(nome);   
    }
    
    public void adicionarAresta(String origem, String destino){
        Aresta a = new Aresta(verticesMap.get(origem), verticesMap.get(destino));  
        arestas.add(a);     
        arestasMap.put(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome(), a);
    }
    
    public void removerAresta(String origem, String destino){
        arestasMap.remove(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome());
        arestas.remove(arestasMap.get(verticesMap.get(origem).getNome() + verticesMap.get(destino).getNome()));
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
