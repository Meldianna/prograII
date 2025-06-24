package interfaces;

import java.util.Set;

public interface INodo<T> {

	/*
	 PRE: -
	 POST: Lista creada y asociada a cada instancia de interfaces.INodo
	 AX: - 
	 */
	//List<INodo<T>> getVecinos(); //constante
	public String getNombre();
	/*getters & setters*/
	T getValor(); //valor de tipo genérico
	void setValor(T valor);
	int getPeso();
	void setPeso(int peso);
	

	void agregarVecino(INodo<T> vecino);
	Set getVecinos();
	//void agregarVecino(String nombre, int peso);
}

