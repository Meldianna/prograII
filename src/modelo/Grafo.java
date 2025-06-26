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

    private Set<String> nombreContenidos= new HashSet<>();

    @Override
    public void agregarNodo(int id, T valor, String nombreContenido) {// id sería la clave del "diccionario"; valor sería el valor asociado a esa clave,
        //en este caso, "valor" sería un objeto llamado persona. Entonces el Nodo sería una persona.
        if (!nodos.containsKey(id) && (!nombreContenidos.contains(nombreContenido))){//si la clave "id" se encuentra NO dentro del mapa
            nodos.put(id, new Nodo<T>(valor));//agregamos al mapa la clave "id" y su nuevo valor (un INodo de tipo <T>)
            nombreContenidos.add(nombreContenido);

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


    public Collection<INodo<T>> getTodosLosNodos() {
        return nodos.values();
    }

    public INodo<T> buscarNodoPorId(int id) {
        return nodos.get(id);
    }


}