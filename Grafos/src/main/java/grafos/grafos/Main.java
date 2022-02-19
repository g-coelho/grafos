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
        Grafo g = new Grafo(true, false);
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B", 0);
        g.adicionarAresta("A", "C", 0);
        g.adicionarAresta("C", "B", 0);


    }
    
}
