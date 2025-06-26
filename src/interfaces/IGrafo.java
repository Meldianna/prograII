package interfaces;
import java.util.Collection;
import java.util.Comparator;
public interface IGrafo<T> {
    // pre: nodos deben existir
    // post: los nodos deben estar conectados y ser agregados a sus mapas de vecinos
    void agregarArista(int origen, int destino);

    /*EXPLICACIÓN: Nuestro grafo tiene nodos de tipo INodo<T>, por lo tanto no conocemos el valor del nodo.
        Nuestra estructura, entonces, para evitar que puedan agregarse valores iguales, se compone de:
            1 HashMap y un Set -> el mapa guarda clave-valor de cada nodo agregado al grafo ( {id, T valor} );
            1 Set -> se guardan las descripciones del contenido del INodo. En nuestro caso, el nombre de la localidad
    dentro del atributo "valor" se guarda el
*/

    //pre: -
    //post: Debe haberse agregado un nodo con su id y valor de tipo genérico al mapa "nodos". El valor del nodo debe ser un objeto que reciba como id el "nombreContenido"
    //para guardarse en el conjunto de nombreContenidos
    public void agregarNodo(int id, T valor, String nombreContenido);

    //pre:  el mapa de nodos no debe estar vacío
    //post: retorna una colección de todos los nodos del mapa
    public Collection<INodo<T>> getTodosLosNodos();
}