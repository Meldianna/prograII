package interfaces;

import java.util.Map;

public interface INodo<T> {

	String getNombre();

	T getValor();
	void setValor(T valor);

	int getPeso();
	void setPeso(int peso);

	void agregarVecino(INodo<T> vecino, int peso);

	Map<INodo<T>, Integer> getVecinos();

}

