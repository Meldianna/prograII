package test;

import modelo.Grafo;
import modelo.Localidad;
import interfaces.INodo;

import java.util.Map;

public class testGrafos {
    public static void main(String[] args) {
        // Crear el grafo de tipo Localidad
        Grafo<Localidad> grafo = new Grafo<>();

        // Crear localidades
        Localidad loc1 = new Localidad(1000, "Palermo", "CABA");
        Localidad loc2 = new Localidad(2000, "Recoleta", "CABA");
        Localidad loc3 = new Localidad(3000, "Caballito", "CABA");

        // Agregar nodos al grafo
        grafo.agregarNodo(1, loc1);
        grafo.agregarNodo(2, loc2);
        grafo.agregarNodo(3, loc3);

        // Agregar conexiones con peso
        grafo.agregarArista(1, 2, 5); // Palermo <-> Recoleta (peso 5)
        grafo.agregarArista(2, 3, 10); // Recoleta <-> Caballito (peso 10)
        grafo.agregarArista(1, 3, 20); // Palermo <-> Caballito (peso 20)

        // Mostrar estructura del grafo
        System.out.println("== Matriz de Adyacencia ==");
        grafo.mostrarMatrizAdyacencia();

        System.out.println("\n== Lista de Adyacencia ==");
        grafo.mostrarListaAdyacencia();

        // Ejecutar Dijkstra desde Palermo (id = 1)
        System.out.println("\n== Dijkstra desde Palermo ==");
        Map<INodo<Localidad>, Integer> distancias = grafo.dijkstra(1);
        for (Map.Entry<INodo<Localidad>, Integer> entrada : distancias.entrySet()) {
            Localidad loc = entrada.getKey().getValor();
            int distancia = entrada.getValue();
            System.out.println("Distancia hasta " + loc.getNombre() + ": " + distancia);
        }

        // Prueba de recorrido BFS
        System.out.println("\n== BFS desde Palermo ==");
        grafo.bfs(1);

        // Prueba de recorrido DFS
        System.out.println("\n== DFS desde Palermo ==");
        grafo.dfs(1);
    }
}

