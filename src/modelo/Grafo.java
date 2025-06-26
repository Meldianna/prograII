package modelo;


import java.util.Collection; // MODIFICADO: Añadido para el tipo de retorno de getNodos
import java.util.HashMap;
import java.util.Map;
// Se eliminan las importaciones de List e INodo que ya no se usan directamente aquí.

public class Grafo<T> { // MODIFICADO: Clase ahora genérica con <T>

    // MODIFICADO: El mapa ahora asocia un valor T con su correspondiente Nodo<T>.
    private Map<T, Nodo<T>> nodos = new HashMap<>();

    // MODIFICADO: El método acepta un valor de tipo T.
    public void agregarNodo(T valor) {
        nodos.putIfAbsent(valor, new Nodo<>(valor));
    }

    // MODIFICADO: Los parámetros ahora son de tipo T para identificar los nodos.
    public void agregarArista(T origen, T destino, int peso) {
        Nodo<T> nodoOrigen = nodos.get(origen); // MODIFICADO: Búsqueda por valor T
        Nodo<T> nodoDestino = nodos.get(destino); // MODIFICADO: Búsqueda por valor T
        if (nodoOrigen != null && nodoDestino != null) {
            nodoOrigen.agregarVecino(nodoDestino, peso);
            nodoDestino.agregarVecino(nodoOrigen, peso); // Si es no dirigido
        }
    }

    // MODIFICADO: Adaptado al nuevo mapa de adyacencias del Nodo.
    public void mostrarListaAdyacencia() {
        for (Nodo<T> nodo : nodos.values()) { // MODIFICADO: Itera sobre Nodo<T>
            System.out.print(nodo.getValor() + ": ");
            // MODIFICADO: Itera sobre el Map.Entry para obtener vecino y peso juntos.
            for (Map.Entry<Nodo<T>, Integer> adyacencia : nodo.getAdyacencias().entrySet()) {
                System.out.print("(" + adyacencia.getKey().getValor() + ", peso=" + adyacencia.getValue() + ") ");
            }
            System.out.println();
        }
    }

    // MODIFICADO: Acepta un valor T y devuelve un Nodo<T>.
    public Nodo<T> getNodo(T valor) {
        return nodos.get(valor);
    }

    // MODIFICADO: Devuelve una colección de los nuevos objetos Nodo<T>.
    public Collection<Nodo<T>> getNodos() {
        return nodos.values();
    }
}