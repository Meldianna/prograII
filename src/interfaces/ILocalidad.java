package interfaces;

public interface ILocalidad {


	///PRE: La localidad debe tener un nombre
	///POST: Retorna un String con el nombre
	public String getNombre();


	public String toString();

	///PRE: La localidad debe tener un código postal de tipo objeto Integer
	///POST: Retorna el código postal
	public int getCodigoPostal();

	///PRE: La localidad debe estar asociada a una provincia
	///POST: Retorna un String con el nombre de la provincia
	public String getProvincia();

}