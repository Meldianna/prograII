package test;

import modelo.Dijkstra;
import modelo.Grafo;
import modelo.Localidad;
import interfaces.INodo;

import java.util.*;

public class testGrafos {
    public static void main(String[] args) {
        // Crear el grafo de tipo Localidad
        Grafo<Localidad> grafo = new Grafo<>();

        // Crear localidades
        Localidad loc1 = new Localidad(1000, "Palermo", "CABA");
        Localidad loc2 = new Localidad(2000, "Recoleta", "CABA");
        Localidad loc3 = new Localidad(3000, "Caballito", "CABA");
        Localidad loc4 = new Localidad(4000, "Villa Urquiza", "CABA");


        // Agregar nodos al grafo
        grafo.agregarNodo(1, loc1);
        grafo.agregarNodo(2, loc2);
        grafo.agregarNodo(3, loc3);
        grafo.agregarNodo(4, loc4);


        // Agregar conexiones con peso
        grafo.agregarArista(1, 2, 5);  // Palermo <-> Recoleta
        grafo.agregarArista(2, 3, 10); // Recoleta <-> Caballito
        grafo.agregarArista(1, 3, 20); // Palermo <-> Caballito
        grafo.agregarArista(4, 2, 6); // Villa Urquiza - Recoleta
        grafo.agregarArista(4, 3, 8); // Villa Urquiza - Caballito


        // Mostrar estructura del grafo
        System.out.println("== Matriz de Adyacencia ==");
        grafo.mostrarMatrizAdyacencia();

        System.out.println("\n== Lista de Adyacencia ==");
        grafo.mostrarListaAdyacencia();

        // Ejecutar Dijkstra desde Palermo (id = 1)
        INodo<Localidad> origen = grafo.buscarNodoPorId(1);
        Map<INodo<Localidad>, INodo<Localidad>> padres = new HashMap<>();
        Dijkstra<Localidad> solver = new Dijkstra<>(grafo);
        Map<INodo<Localidad>, Integer> distancias = solver.calcularDistancias(origen, padres);

        // Mostrar distancias y caminos
        System.out.println("\n== Dijkstra desde Palermo ==");
        for (Map.Entry<INodo<Localidad>, Integer> entrada : distancias.entrySet()) {
            Localidad destino = entrada.getKey().getValor();
            int distancia = entrada.getValue();
            System.out.print("Distancia hasta " + destino.getNombre() + ": " + distancia);

            List<INodo<Localidad>> camino = solver.reconstruirCamino(entrada.getKey(), padres);
            System.out.print(" | Camino: ");
            for (INodo<Localidad> paso : camino) {
                System.out.print(paso.getValor().getNombre() + " ");
            }
            System.out.println();
        }

        // Recorridos
        System.out.println("\n== BFS desde Palermo ==");
        grafo.bfs(1);

        System.out.println("\n== DFS desde Palermo ==");
        grafo.dfs(1);
    }
}