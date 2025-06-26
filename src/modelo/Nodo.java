package modelo;

import java.util.Map; // MODIFICADO: Importamos Map en lugar de List
import java.util.HashMap; // MODIFICADO: Añadido para la nueva estructura
import java.util.Objects; // MODIFICADO: Añadido para implementar equals/hashCode de forma segura

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