package modelo;

import interfaces.ILocalidad;

public class Localidad implements ILocalidad{
	private String provincia; //cambiamos el DNI a tipo entero
    private String nombre;
    private int codigoPostal;
 
    public Localidad(int numero, String nombre, String provincia) {
        this.codigoPostal = numero;
        this.nombre = nombre;
        this.provincia = provincia;
    }


	@Override
	public int getCodigoPostal() {
		return this.codigoPostal;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String getProvincia() {
		return this.provincia;
	}

	@Override
	public String toString() {
		return nombre + " (" + provincia + ", CP: " + codigoPostal + ")";
	}

}
