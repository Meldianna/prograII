package modelo;
import interfaces.INodo;

import java.util.ArrayList;
import java.util.List;

public class NodoTP3<T> implements INodo<T>{

    private int id; //agregamos que el nodo sepa cuál es su ID --> hace más sencillo la lógica de los metodos del grafo. :)
    private T valor;
    private List<INodo<T>> vecinos; //en un grafo, cada nodo tiene una lista de sus nodos adyacentes

    public NodoTP3(int id, T valor){
        this.valor = valor;
        this.id = id;
        this.vecinos = new ArrayList<>(); //inicializamos el array
    }
    @Override
    public List<INodo<T>> getVecinos() {
        return List.of();
    }

    public int getId() {
        return id;
    }

    @Override
    public T getValor() {
        return null;
    }

    @Override
    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public void agregarVecino(INodo<T> vecino) {
        if (!vecinos.contains(vecino)) {
            vecinos.add(vecino);
        }
    }
}

