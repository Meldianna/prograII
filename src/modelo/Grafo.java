package modelo;
import java.util.*;
import interfaces.IGrafo;
import interfaces.INodo;

//grafo NO dirigido
public class Grafo<T> implements IGrafo<T>{

    private Map<Integer, INodo<T>> nodos = new HashMap<>();//el grafo tiene

    @Override
    public void agregarNodo(int id, T valor) {// id sería la clave del "diccionario"; valor sería el valor asociado a esa clave,
        //en este caso, "valor" sería un objeto llamado persona. Entonces el Nodo sería una persona.
        if (!nodos.containsKey(id)){//si la clave "id" se encuentra NO dentro del mapa
            nodos.put(id, new Nodo<T>(id, valor));//agregamos al mapa la clave "id" y su nuevo valor (un INodo de tipo <T>)
        }
    }

    // con este metodo agregamos una arista (conexión) entre dos nodos existentes
    @Override
    public void agregarArista(int idOrigen, int idDestino) {//idOrigen e idDestino son los ID de los nodos existentes que deseamos conectar.
        if (nodos.containsKey(idOrigen) && nodos.containsKey(idDestino)){

            INodo nodoOrigen = nodos.get(idOrigen);//Agarro el nodo que tiene como clave al origen. get() retorna el valor de la clave, o sea, su INodo<T> asociado
            INodo nodoDestino = nodos.get(idDestino);//lo mismo, pero el nodo que tiene como clave al destino.

            //establecemos la conexión *bidireccional* entre los nodos que queremos unir.
            nodoOrigen.agregarVecino(nodoDestino);
            nodoDestino.agregarVecino(nodoOrigen);
        }
    }

    @Override
    public void mostrarMatrizAdyacencia() {
        // El método keySet() devuelve un Set (un conjunto, si recuerdan de progra 1)
        //Un Set es una colección que no permite elementos duplicados, por lo que cada clave aparecerá una sola vez en el conjunto devuelto.
        List<Integer> clavesNodos = new ArrayList<>(nodos.keySet());//Se crea una lista (llamada "clavesNodos") CON los valores del conjunto (que son enteros)
        Collections.sort(clavesNodos);//ordenamos (supongo de mayor a menor(?)) los enteros de la lista nueva


        System.out.println("Mostrando la matriz de adyacencia de " + this.getClass().getName());
        System.out.print("   ");

        //creamos el "encabezado" de la matriz (ver imagen)
        for (int i : clavesNodos) System.out.print(i + " ");
        System.out.println();

        // Filas de la matriz
        for (int i : clavesNodos) { //nos paramos en las COLUMNAS (que son las claves: 1,2,3... etc)
            System.out.print(i + ": ");
            for (int j : clavesNodos) {  //ahora recorremos las FILAS (la misma cantidad de columnas)
                INodo<T> nodoI = nodos.get(i);//agarramos el nodo que tiene de clave el número de i
                INodo<T> nodoJ = nodos.get(j);//agarramos el nodo que tiene de clave el número de j
                System.out.print(nodoI.getVecinos().contains(nodoJ) ? "1 " : "0 "); //un IF, si el nodo cuya clave es el numero de j (fila) se encuentra
                //dentro de la lista de vecinos del nodo cuya clave es el número de i (columna), se printea un 1, sino un 0
            }
            System.out.println();
        }
    }

    @Override
    public void mostrarListaAdyacencia() {
        //Por cada nodo del grafo, se crea una lista de todos los nodos que son sus vecinos directos (es decir, los nodos a los que está conectado por una arista)
        for (Map.Entry<Integer, INodo<T>> entrada : nodos.entrySet()) {//cuesta entenderlo, pero se traduce en:
            //nodos --> nuestro mapa principal
            //entrySet() --> devuelve TODO el conjunto de pares "clave-valor" del mapa: no solo la clave (como con keySet()) o solo el valor (como values())
            //Map.Entry<Integer, INodo<T>> --> Este es el tipo de dato de cada "par" individual que nos devuelve .entrySet().
            // Un Map.Entry es un objeto pequeño que contiene exactamente una clave y un valor.
            // definimos nuestra variable "entrada" que, en cada vuelta del for, contiene el par "clave-valor" actual

            System.out.print(entrada.getKey() + ": ");
            //creamos una nueva lista con los vecinos directos de cada uno de los nodos
            List<INodo<T>> vecinos = entrada.getValue().getVecinos();//como un ""puntero"" a la lista vecinos del nodo
            //asignamos una variable "vecinos" que contiene los vecinos directos de un nodo especifico

            for (INodo<T> vecino : vecinos) {//recorriendo la lista de vecinos, imprimiendo cada vecino dentro de la lista.
                System.out.print(vecino.getValor() + " ");
            }
            System.out.println();
        }
    }


    @Override
    public void bfs(int idInicio) {
        //explorar todos los nodos del grafo de manera sistemática, comenzando desde un nodo raíz y visitando primero todos los nodos vecinos a la raíz,
        // luego los vecinos de esos vecinos, y así sucesivamente, nivel por nivel
        if (!nodos.containsKey(idInicio)) return;

        //creamos un conjunto con los vecinos y una cola con los nodos a recorrer
        Set<Integer> visitados = new HashSet<>();
        Queue<INodo<T>> cola = new LinkedList<>();

        INodo<T> nodoInicio = nodos.get(idInicio);
        cola.add(nodoInicio);
        visitados.add(nodoInicio.getId());

        System.out.println("Recorrido BFS:");
        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            System.out.print(actual.getValor() + " ");

            for (INodo<T> vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino.getId())) {
                    visitados.add(vecino.getId());
                    cola.add((INodo<T>) vecino);
                }
            }

        }
    }
    //reutilizar el callback anterior para comparar el nodo
    //hacer otro callback para retornar un valor entero asociado a un nodo particular
    //


    @Override
    public void dfs(int inicio) {

    }
}
