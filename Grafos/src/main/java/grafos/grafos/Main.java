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
        Grafo g = new Grafo();
        g.adicionarVertice("A");
        g.adicionarVertice("B");
        g.adicionarVertice("C");
        g.adicionarAresta("A", "B");
        g.adicionarAresta("A", "C");
        g.adicionarAresta("C", "B");
        System.out.println(g.toString());  
        g.removerVertice("A");
        System.out.println(g.toString());
    }
    
}
