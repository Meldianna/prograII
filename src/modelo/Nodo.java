package modelo;
import interfaces.INodo;
import java.util.*;

public class Nodo<T> implements INodo<T>{
	
    private String nombreLocalidad; //agregamos que el nodo sepa cuál es su ID --> hace más sencillo la lógica de los metodos del grafo. :)
    private T valor;
    private int peso;
    private Map<String, Integer> vecinos; //en un grafo, cada nodo tiene una lista de sus nodos adyacentes. La clave que identifica
    //a cada nodo es el nombre de la Localidad, y como valor asociado es el peso (esfuerzo que conlleva ir al nodo, simulando peso de la arista)

    public Nodo(String nombre, T valor, int peso){
        this.valor = valor;
        this.nombreLocalidad= nombre;
        this.peso = peso;
        this.vecinos = new HashMap<>(); //inicializamos el array
    }
    @Override
    public Set<Map.Entry<String, Integer>> getVecinos() {
        return vecinos.entrySet(); //retorna un Set con todos los vecinos en pares clave-valor
    }

    public void agregarVecino(String nombre, int peso) {
            vecinos.putIfAbsent(nombre, peso);
    }
    
    
    //implementar un try-catch para no permitir nulos. Por defecto, HashMap permite claves de valor null, pero 
    //no es nuestro caso
	@Override
	public void agregarVecino(INodo<T> vecino) {
		try {
		vecinos.putIfAbsent(vecino.getNombre(), vecino.getPeso());
		}
	
	
    
    public String getNombre() {
        return nombreLocalidad;
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
    public int getPeso() {
	return this.peso;
    }
    @Override
    public void setPeso(int peso) {
	this.peso = peso;
    }

   
	
}
