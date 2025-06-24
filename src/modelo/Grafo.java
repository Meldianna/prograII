package modelo;

import java.util.*;
import interfaces.IGrafo;
import interfaces.INodo;

// Grafo NO dirigido con pesos en aristas y algoritmo Dijkstra
public class Grafo<T> implements IGrafo<T> {
    @Override
    public void agregarArista(int origen, int destino) {

    }

    private Map<Integer, INodo<T>> nodos = new HashMap<>();

    @Override
    public void agregarNodo(int id, T valor) {
        if (!nodos.containsKey(id)) {
            // Creo el nodo con peso 0 (puede representar costo base o ignorarse)
            nodos.put(id, new Nodo<T>(String.valueOf(id), valor, 0));
        }
    }

    // Agregar arista con peso entre nodos existentes
    public void agregarArista(int idOrigen, int idDestino, int peso) {
        INodo<T> nodoOrigen = nodos.get(idOrigen);
        INodo<T> nodoDestino = nodos.get(idDestino);
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarVecino(nodoDestino, peso);
            nodoDestino.agregarVecino(nodoOrigen, peso); // Grafo no dirigido
        }
    }

    // Implementación del algoritmo de Dijkstra para rutas mínimas desde idOrigen
    public Map<INodo<T>, Integer> dijkstra(int idOrigen) {
        Map<INodo<T>, Integer> distancias = new HashMap<>();
        Set<INodo<T>> visitados = new HashSet<>();

        INodo<T> origen = nodos.get(idOrigen);
        if (origen == null) return distancias;

        // Inicializar distancias a infinito excepto origen
        for (INodo<T> nodo : nodos.values()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }
        distancias.put(origen, 0);

        // PriorityQueue ordenada por la distancia mínima actual
        PriorityQueue<INodo<T>> cola = new PriorityQueue<>(Comparator.comparingInt(distancias::get));
        cola.add(origen);

        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            // Revisar vecinos y actualizar distancias
            for (Map.Entry<INodo<T>, Integer> entrada : actual.getVecinos().entrySet()) {
                INodo<T> vecino = entrada.getKey();
                int pesoArista = entrada.getValue();

                int nuevaDistancia = distancias.get(actual) + pesoArista;
                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    cola.add(vecino);
                }
            }
        }
        return distancias;
    }

    @Override
    public void mostrarMatrizAdyacencia() {
        List<Integer> claves = new ArrayList<>(nodos.keySet());
        Collections.sort(claves);

        System.out.println("Matriz de adyacencia con pesos (0 = sin conexión):");
        System.out.print("    ");
        for (int c : claves) System.out.printf("%4d", c);
        System.out.println();

        for (int i : claves) {
            System.out.printf("%4d", i);
            INodo<T> nodoI = nodos.get(i);
            for (int j : claves) {
                INodo<T> nodoJ = nodos.get(j);
                // Buscar peso en vecinos, si no hay conexión, mostrar 0
                int peso = 0;
                Integer encontrado = null;
                for (Map.Entry<INodo<T>, Integer> vecino : nodoI.getVecinos().entrySet()) {
                    if (vecino.getKey().equals(nodoJ)) {
                        encontrado = vecino.getValue();
                        break;
                    }
                }
                peso = (encontrado != null) ? encontrado : 0;
                System.out.printf("%4d", peso);
            }
            System.out.println();
        }
    }

    @Override
    public void mostrarListaAdyacencia() {
        for (Map.Entry<Integer, INodo<T>> entrada : nodos.entrySet()) {
            System.out.print(entrada.getKey() + ": ");
            for (Map.Entry<INodo<T>, Integer> vecino : entrada.getValue().getVecinos().entrySet()) {
                System.out.print(vecino.getKey().getValor() + "(" + vecino.getValue() + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void bfs(int idInicio) {
        if (!nodos.containsKey(idInicio)) return;

        Set<Integer> visitados = new HashSet<>();
        Queue<INodo<T>> cola = new LinkedList<>();

        INodo<T> nodoInicio = nodos.get(idInicio);
        cola.add(nodoInicio);
        visitados.add(idInicio);

        System.out.print("Recorrido BFS: ");
        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            System.out.print(actual.getValor() + " ");

            for (Map.Entry<INodo<T>, Integer> vecino : actual.getVecinos().entrySet()) {
                INodo<T> nodoVecino = vecino.getKey();
                int idVecino = Integer.parseInt(nodoVecino.getNombre()); // asumo nombre = id como string
                if (!visitados.contains(idVecino)) {
                    visitados.add(idVecino);
                    cola.add(nodoVecino);
                }
            }
        }
        System.out.println();
    }

    @Override
    public void dfs(int idInicio) {
        if (!nodos.containsKey(idInicio)) return;
        Set<Integer> visitados = new HashSet<>();
        System.out.print("Recorrido DFS: ");
        dfsRecursivo(nodos.get(idInicio), visitados);
        System.out.println();
    }

    private void dfsRecursivo(INodo<T> nodo, Set<Integer> visitados) {
        int id = Integer.parseInt(nodo.getNombre()); // asumo nombre = id como string
        if (visitados.contains(id)) return;

        System.out.print(nodo.getValor() + " ");
        visitados.add(id);

        for (Map.Entry<INodo<T>, Integer> vecino : nodo.getVecinos().entrySet()) {
            dfsRecursivo(vecino.getKey(), visitados);
        }
    }
}

