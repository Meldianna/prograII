package modelo;

import interfaces.INodo;
import java.util.*;

public class Nodo<T> implements INodo<T> {


    private T valor;                 // Información que guarda el nodo (por ej. Localidad)

    // Vecinos con peso: clave = vecino (nodo), valor = peso de la arista que conecta
    private Map<INodo<T>, Integer> vecinos;

    public Nodo(T valor) {
        this.valor = valor;

        this.vecinos = new HashMap<>();
    }


    // Agrega vecino y su peso solo si no existe ya
    public void agregarVecino(INodo<T> vecino, int id) {
        if (vecino != null) {
            vecinos.putIfAbsent(vecino, id);
        }
    }

    @Override
    public Map<INodo<T>, Integer> getVecinos() {
        return vecinos;
    }


    @Override
    public T getValor() {
        return valor;
    }

    @Override
    public void setValor(T valor) {
        this.valor = valor;
    }

    public String toString() {
        // Llama al método toString() del objeto 'valor' que contiene.
        return String.valueOf(this.valor);
    }


}


