/*
* 1. Clase Nodo (Refactorizada)

        La clase ahora es genérica (<T>) y utiliza un Map para las adyacencias. Se añaden equals y hashCode para que funcione correctamente como clave en un HashMap.

* */

import java.util.Map; // MODIFICADO: Importamos Map en lugar de List
import java.util.HashMap; // MODIFICADO: Añadido para la nueva estructura
import java.util.Objects; // MODIFICADO: Añadido para implementar equals/hashCode de forma segura
import java.util.Collection; // MODIFICADO: Añadido para el tipo de retorno de getNodos
import java.util.PriorityQueue;

public class Nodo<T> { // MODIFICADO: Clase ahora genérica con <T>

    private T valor; // MODIFICADO: El valor es de tipo genérico T

    // MODIFICADO: Reemplazamos las dos Listas por un único Map.
    // La clave es el Nodo vecino y el valor es el peso de la arista.
    private Map<Nodo<T>, Integer> adyacencias = new HashMap<>();

    public Nodo(T valor) { // MODIFICADO: El constructor acepta un valor de tipo T
        this.valor = valor;
    }

    public T getValor() { // MODIFICADO: Devuelve un valor de tipo T
        return valor;
    }

    // MODIFICADO: El método ahora es más simple y seguro.
    public void agregarVecino(Nodo<T> vecino, int peso) {
        adyacencias.put(vecino, peso);
    }

    // MODIFICADO: Nuevo método para obtener el mapa de adyacencias.
    // Es la forma más eficiente para que Dijkstra itere sobre los vecinos.
    public Map<Nodo<T>, Integer> getAdyacencias() {
        return adyacencias;
    }

    // --- MÉTODOS AÑADIDOS PARA EL CORRECTO FUNCIONAMIENTO COMO CLAVE DE MAPA ---

    @Override // AÑADIDO: Sobrescritura del método hashCode.
    public int hashCode() {
        // Es crucial para que los HashMaps funcionen correctamente con Nodo como clave.
        return Objects.hash(valor);
    }

    @Override // AÑADIDO: Sobrescritura del método equals.
    public boolean equals(Object obj) {
        // Define que dos Nodos son "iguales" si sus valores internos son iguales.
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nodo<?> otroNodo = (Nodo<?>) obj;
        return Objects.equals(valor, otroNodo.valor);
    }
}

/*2. Clase Grafo (Refactorizada)

Esta clase también se vuelve genérica y se adapta para usar la nueva clase Nodo<T>.

*/

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

/*3. Clase Dijkstra (Refactorizada)

Finalmente, la clase Dijkstra se vuelve genérica y se adapta para usar la estructura de datos más robusta.

*/

// Se eliminan importaciones innecesarias como List, Set, INodo.

//import modelo.Grafo; // MODIFICADO: Ahora importa tu Grafo<T>
//import modelo.Nodo;  // MODIFICADO: Ahora importa tu Nodo<T>

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
