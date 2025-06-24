package interfaces;

import java.util.List;

public interface INodoTP3<T> {

    /*
     PRE: -
     POST: Lista creada y asociada a cada instancia de interfaces.INodo
     AX: -
     */
    List<INodo<T>> getVecinos(); //constante
    public int getId();
    /*getters & setters*/
    T getValor(); //valor de tipo genérico
    void setValor(T valor);


    void agregarVecino(INodo<T> vecino);
}
