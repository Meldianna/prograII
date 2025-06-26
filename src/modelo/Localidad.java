package modelo;

import interfaces.ILocalidad;

public class Localidad implements ILocalidad{
	private String provincia;
	private String nombre;


	public Localidad( String nombre, String provincia) {

		this.nombre = nombre;
		this.provincia = provincia;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String getProvincia() {
		return this.provincia;
	}

	public String toString() {

		return this.nombre;
	}

}