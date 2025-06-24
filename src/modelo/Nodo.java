package modelo;

import interfaces.INodo;
import java.util.*;

public class Nodo<T> implements INodo<T> {

    private String nombreLocalidad;  // Identificador único del nodo
    private T valor;                 // Información que guarda el nodo (por ej. Localidad)
    private int peso;                // Peso propio (puede usarse para otros fines, no es peso arista)
    // Vecinos con peso: clave = vecino (nodo), valor = peso de la arista que conecta
    private Map<INodo<T>, Integer> vecinos;

    public Nodo(String nombre, T valor, int peso) {
        this.nombreLocalidad = nombre;
        this.valor = valor;
        this.peso = peso;
        this.vecinos = new HashMap<>();
    }

    // Agrega vecino y su peso solo si no existe ya
    public void agregarVecino(INodo<T> vecino, int peso) {
        if (vecino != null) {
            vecinos.putIfAbsent(vecino, peso);
        }
    }

    @Override
    public Map<INodo<T>, Integer> getVecinos() {
        return vecinos;
    }

    @Override
    public String getNombre() {
        return nombreLocalidad;
    }

    @Override
    public T getValor() {
        return valor;
    }

    @Override
    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public int getPeso() {
        return peso;
    }

    @Override
    public void setPeso(int peso) {
        this.peso = peso;
    }

    // IMPORTANTE: para que los nodos se comparen correctamente en colecciones (Map, Set)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Nodo)) return false;
        Nodo<?> otro = (Nodo<?>) obj;
        return Objects.equals(this.nombreLocalidad, otro.nombreLocalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreLocalidad);
    }
}




