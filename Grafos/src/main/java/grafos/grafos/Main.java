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
        g.adicionarVertice("F");
        
        
        g.adicionarAresta("A", "B", 10);
        g.adicionarAresta("A", "C", 10); 
        g.adicionarAresta("B", "C", 10); 
        
        g.adicionarAresta("D", "E", 10);
        g.adicionarAresta("D", "F", 10); 
        g.adicionarAresta("E", "F", 10);         
        
        
        System.out.println(g.imprimirMatrizCaminho());
        




    }
    
}
