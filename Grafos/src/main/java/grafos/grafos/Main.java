/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos.grafos;

/**
 *
 * @author gabri
 */
public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo(true, true);
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarVertice("D");
        g.adicionarVertice("E");
        
        g.adicionarAresta("A", "B", 1);
        g.adicionarAresta("A", "C", 1); 
        g.adicionarAresta("A", "D", 1); 
        g.adicionarAresta("B", "E", 2);         
        g.adicionarAresta("C", "E", 3); 
        g.adicionarAresta("D", "E", 4);         
        
        
        System.out.println(g.imprimirDijkstra());
        




    }
    
}
