package modelo;

import interfaces.IGrafo;
import interfaces.INodo;
import interfaces.IGrafo; 
import java.util.*;

public class Dijkstra<T> {

    private IGrafo<T> grafo;

    public Dijkstra(IGrafo<T> grafo) {
        this.grafo = grafo;
    }

    public Map<INodo<T>, Integer> calcularDistancias(INodo<T> origen, Map<INodo<T>, INodo<T>> padres) {
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
                    padres.put(vecino, actual); // Guardamos el camino
                    cola.add(vecino);
                }
            }
        }

        return distancias;
    }

    // Reconstruir camino desde el nodo origen
    public List<INodo<T>> reconstruirCamino(INodo<T> destino, Map<INodo<T>, INodo<T>> padres) {
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

