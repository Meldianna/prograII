package interfaces;

import java.util.Map;

public interface INodo<T> {


	T getValor();
	void setValor(T valor);


	void agregarVecino(INodo<T> vecino, int peso);

	Map<INodo<T>, Integer> getVecinos();

}

