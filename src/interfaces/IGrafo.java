package interfaces;

public interface IGrafo<T> {
    void agregarNodo(int id, T valor); // pre: el valor no debe estar ya en el grafo
    void agregarArista(int origen, int destino); // pre: nodos deben existir



}