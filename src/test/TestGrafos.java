package test;

import modelo.Dijkstra;
import modelo.Grafo;
import modelo.Localidad;
import interfaces.INodo;

import java.util.*;

public class TestGrafos {
    public static void main(String[] args) {
        // Crear el grafo de tipo Localidad
        Grafo<Localidad> grafo = new Grafo<>();

        // Crear localidades
        Localidad loc1 = new Localidad("Palermo", "CABA");
        Localidad loc2 = new Localidad("Recoleta", "CABA");
        Localidad loc3 = new Localidad("Caballito", "CABA");
        Localidad loc4 = new Localidad("Villa Urquiza", "CABA");
        Localidad loc5 = new Localidad("Saveedra", "CABA");
        Localidad loc6 = new Localidad("Devoto", "CABA");




        // Agregar nodos al grafo+

        grafo.agregarNodo(1, loc1, "Palermo");
        grafo.agregarNodo(2, loc2, "Recoleta");
        grafo.agregarNodo(3, loc3, "Caballito");
        grafo.agregarNodo(4, loc4, "Villa Urquiza");
        grafo.agregarNodo(5, loc5, "Saveedra");
        grafo.agregarNodo(6, loc6, "Devoto");



        // Agregar conexiones con peso
        grafo.agregarArista(1, 2, 5);  // Palermo <-> Recoleta
        grafo.agregarArista(2, 3, 1); // Recoleta <-> Caballito
        grafo.agregarArista(1, 3, 3); // Palermo <-> Caballito
        grafo.agregarArista(4, 2, 6); // Villa Urquiza - Recoleta
        grafo.agregarArista(4, 3, 8); // Villa Urquiza - Caballito
        grafo.agregarArista(5, 4, 10);
        grafo.agregarArista(6, 5, 20);



        // EJECUCIÓN DEL ALGORITMO DE DIJKSTRA
        // Calculamos y mostramos la ruta más corta desde "Retiro" (ID 1).
        System.out.println("\n--- Calculando rutas desde Retiro (ID 1) ---");
        Dijkstra.ejecutar(grafo, 1);

        // Podemos volver a llamar al método para calcular desde otro origen.
        System.out.println("\n--- Calculando rutas desde Constitución (ID 2) ---");
        Dijkstra.ejecutar(grafo, 2);


    }
}
