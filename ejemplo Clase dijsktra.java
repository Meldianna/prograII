package servicios;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
// Se eliminan importaciones innecesarias como List, Set, INodo.

import modelo.Grafo; // MODIFICADO: Ahora importa tu Grafo<T>
import modelo.Nodo;  // MODIFICADO: Ahora importa tu Nodo<T>

public class Dijkstra<T> { // MODIFICADO: La clase es genérica.

    // MODIFICADO: El método es ahora una instancia de método y usa tipos genéricos.
    public void ejecutar(Grafo<T> grafo, T valorOrigen) {
        
        // MODIFICADO: El mapa de distancias ahora usa el objeto Nodo<T> como clave.
        Map<Nodo<T>, Integer> distancias = new HashMap<>();

        // AÑADIDO: Cola de prioridad que ordena Nodos según su distancia en el mapa.
        // Se define el comparador usando una expresión lambda.
        PriorityQueue<Nodo<T>> cola = new PriorityQueue<>(
            (n1, n2) -> Integer.compare(distancias.get(n1), distancias.get(n2))
        );

        // MODIFICADO: La inicialización itera sobre los objetos Nodo<T>.
        for (Nodo<T> nodo : grafo.getNodos()) {
            distancias.put(nodo, Integer.MAX_VALUE);
        }

        // MODIFICADO: Obtenemos el nodo origen a partir de su valor T.
        Nodo<T> nodoOrigen = grafo.getNodo(valorOrigen);
        if (nodoOrigen == null) { // AÑADIDO: Chequeo de seguridad.
            System.out.println("El nodo de origen no existe.");
            return;
        }

        // MODIFICADO: La clave para la distancia es el objeto Nodo<T>.
        distancias.put(nodoOrigen, 0);
        
        // MODIFICADO: Se añade el objeto Nodo<T> directamente a la cola.
        cola.add(nodoOrigen);

        // Ya no se necesita el conjunto 'visitados' porque la lógica de la cola de prioridad
        // y la comprobación de distancias lo hacen implícitamente redundante en muchas implementaciones.

        while (!cola.isEmpty()) {
            // MODIFICADO: Obtenemos el objeto Nodo<T> con la menor distancia.
            Nodo<T> u = cola.poll();

            // MODIFICADO: Bucle principal ahora itera sobre el mapa de adyacencias.
            // Esto es más seguro y semántico que usar dos listas paralelas.
            for (Map.Entry<Nodo<T>, Integer> adyacencia : u.getAdyacencias().entrySet()) {
                Nodo<T> v = adyacencia.getKey();
                int pesoArista = adyacencia.getValue();

                // MODIFICADO: La lógica de relajación ahora usa los objetos Nodo<T>.
                int nuevaDistancia = distancias.get(u) + pesoArista;

                if (nuevaDistancia < distancias.get(v)) {
                    distancias.put(v, nuevaDistancia);
                    
                    // MODIFICADO: Actualiza la posición del nodo en la cola de prioridad.
                    cola.remove(v); // Quita la entrada antigua.
                    cola.add(v);    // Añade la nueva con la prioridad actualizada.
                }
            }
        }

        // MODIFICADO: La impresión de resultados se adapta a la nueva estructura.
        System.out.println("Distancias mínimas desde " + valorOrigen + ":");
        for (Map.Entry<Nodo<T>, Integer> entry : distancias.entrySet()) {
            System.out.println("A " + entry.getKey().getValor() + " = " + entry.getValue());
        }
    }
}
