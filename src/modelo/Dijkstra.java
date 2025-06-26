package modelo;

import interfaces.IGrafo;
import interfaces.INodo;
import java.util.*;

public class Dijkstra<T> {

    // El constructor y la variable de instancia se eliminan porque ahora usaremos métodos estáticos.
    /*
    private IGrafo<T> grafo;
    public Dijkstra(IGrafo<T> grafo) {
        this.grafo = grafo;
    }
    */

    /**
     * MÉTODO ESTÁTICO PRINCIPAL.
     * Encapsula la lógica para ejecutar el algoritmo de Dijkstra y mostrar los resultados.
     * @param grafo El grafo sobre el cual se ejecutará el algoritmo.
     * @param idOrigen El ID del nodo de inicio.
     */
    public static <T> void ejecutar(IGrafo<T> grafo, int idOrigen) {

        // 1. Buscamos el nodo de origen.
        INodo<T> origen = grafo.buscarNodoPorId(idOrigen);
        if (origen == null) {
            System.out.println("Error: El nodo de origen con ID " + idOrigen + " no existe.");
            return;
        }

        // 2. Preparamos el mapa para guardar los caminos.
        Map<INodo<T>, INodo<T>> padres = new HashMap<>();

        // 3. Calculamos las distancias.
        // Llamamos a tu método 'calcularDistancias' (ahora privado y estático).
        Map<INodo<T>, Integer> distancias = calcularDistancias(grafo, origen, padres);

        // 4. Mostramos los resultados.
        System.out.println("--- Resultados de Dijkstra desde: " + origen.getValor() + " ---");
        for (INodo<T> destino : grafo.getTodosLosNodos()) {
            Integer distancia = distancias.get(destino);

            System.out.print("Al nodo " + destino.getValor() + ": ");

            if (distancia == Integer.MAX_VALUE) {
                System.out.println("Inalcanzable");
            } else {
                // Llamamos a tu método 'reconstruirCamino' para obtener la ruta.
                List<INodo<T>> camino = reconstruirCamino(destino, padres);
                System.out.println("Distancia mínima = " + distancia + ", Camino = " + camino);
            }
        }
    }

    /**
     * TU MÉTODO ORIGINAL 'calcularDistancias', ahora como un método de ayuda privado y estático.
     * La lógica interna es EXACTAMENTE la misma.
     */
    private static <T> Map<INodo<T>, Integer> calcularDistancias(IGrafo<T> grafo, INodo<T> origen, Map<INodo<T>, INodo<T>> padres) {
        Map<INodo<T>, Integer> distancias = new HashMap<>();
        Set<INodo<T>> visitados = new HashSet<>();
        PriorityQueue<INodo<T>> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        for (INodo<T> nodo : grafo.getTodosLosNodos()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }

        distancias.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            if (!visitados.add(actual)) continue;

            for (Map.Entry<INodo<T>, Integer> entrada : actual.getVecinos().entrySet()) {
                INodo<T> vecino = entrada.getKey();
                int peso = entrada.getValue();
                int nuevaDistancia = distancias.get(actual) + peso;

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    padres.put(vecino, actual);
                    cola.remove(vecino); // Añadir esto mejora la eficiencia
                    cola.add(vecino);
                }
            }
        }
        return distancias;
    }

    /**
     * TU MÉTODO ORIGINAL 'reconstruirCamino', ahora como un método de ayuda privado y estático.
     * La lógica interna es EXACTAMENTE la misma.
     */
    private static <T> List<INodo<T>> reconstruirCamino(INodo<T> destino, Map<INodo<T>, INodo<T>> padres) {
        List<INodo<T>> camino = new ArrayList<>();
        INodo<T> actual = destino;

        while (actual != null) {
            camino.add(actual);
            actual = padres.get(actual);
        }

        Collections.reverse(camino);
        return camino;
    }
}

