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
        Grafo g = new GrafoOrientado(true);
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarVertice("D");
        g.adicionarVertice("E");
        

        g.adicionarAresta("A", "B", 1);
        g.adicionarAresta("A", "C", 1); 
        g.adicionarAresta("A", "D", 1); 
        g.adicionarAresta("A", "E", 2);         
        g.adicionarAresta("B", "C", 1);         
        g.adicionarAresta("B", "D", 2);
        g.adicionarAresta("B", "E", 1);         
        g.adicionarAresta("C", "D", 1); 
        g.adicionarAresta("C", "E", 1);  
        g.adicionarAresta("D", "E", 1); 
        
        System.out.println(g.imprimirMatrizAdjacencia());
        
        
        Grafo h = new GrafoNaoOrientado(true);
        h.adicionarVertice("A");
        h.adicionarVertice("B");
        h.adicionarVertice("C");
        h.adicionarVertice("D");
        h.adicionarVertice("E");
        

        h.adicionarAresta("A", "B", 1);
        h.adicionarAresta("A", "C", 1); 
        h.adicionarAresta("A", "D", 1); 
        h.adicionarAresta("A", "E", 2);         
        h.adicionarAresta("B", "C", 1);         
        h.adicionarAresta("B", "D", 2);
        h.adicionarAresta("B", "E", 1);         
        h.adicionarAresta("C", "D", 1); 
        h.adicionarAresta("C", "E", 1);  
        h.adicionarAresta("D", "E", 1); 

        System.out.println(h.imprimirMatrizAdjacencia());



    }
    
}
