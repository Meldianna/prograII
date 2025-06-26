package interfaces;

import java.util.Map;

public interface INodo<T> {

	//PRE: El nodo debe tener asignado un valor de tipo genérico
	//POST: Retorna el valor, independientemente si se trata de un tipo de dato u objeto
	//AX: if valor == null -> error
	T getValor();

	//PRE: El nodo debe tener un atributo llamado valor
	//POST: El nodo tiene un nuevo valor asociado
	void setValor(T valor);

	//PRE: - El nodo debe tener creado un mapa con sus vecinos y sus pesos (peso de la arista)
	//POST: Debe agregarse el nodo correctamente
	//AX: if vecinos.constains(vecino), entonces -> error
	void agregarVecino(INodo<T> vecino, int peso);

	//PRE: El nodo debe tener creado un mapa de vecinos, independientemente si tiene valores nulos o no
	//POST: Retorna un mapa con todos los vecinos del nodo
	Map<INodo<T>, Integer> getVecinos();

}

