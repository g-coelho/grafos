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
        Grafo g = new Grafo(false, true);
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
        
        System.out.println(g.imprimirPrim());
        g.acoplarArestasPesoMinimo();




    }
    
}
