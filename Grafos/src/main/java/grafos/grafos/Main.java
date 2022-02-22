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
        g.adicionarAresta("A", "B", 7);
        g.adicionarAresta("A", "C", 5);
        g.adicionarAresta("C", "B", 1);
        g.adicionarAresta("A", "A", 0);
        System.out.println(g.retornarGrauEmissao());


    }
    
}
