
public class Arista {

	String vertice1;
	String vertice2;
	Double costo;
	
	public Arista(String vertice1,String vertice2,Double costo)
	{
		this.vertice1=vertice1;
		this.vertice2=vertice2;
		this.costo=costo;

	}
	
	public Double getCosto() {
			return costo;
			
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public String getVertice1() {
		return vertice1;
	}

	public void setVertice1(String vertice1) {
		this.vertice1 = vertice1;
	}

	public String getVertice2() {
		return vertice2;
	}

	public void setVertice2(String vertice2) {
		this.vertice2 = vertice2;
	}
	

}
