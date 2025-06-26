package interfaces;
import java.util.Collection;
import java.util.Comparator;
public interface IGrafo<T> {
    void agregarArista(int origen, int destino); // pre: nodos deben existir

    public void agregarNodo(int id, T valor, String nombreContenido);
   
    Collection<INodo<T>> getTodosLosNodos();
}