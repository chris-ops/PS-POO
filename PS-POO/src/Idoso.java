import java.io.Serializable;

// Christian Trisotto Alegri

public class Idoso extends Passageiro implements Serializable{
	private String rg;

	public Idoso(String nome, String telefone, int idade, String rg) {
		super(nome, telefone, idade);
		this.rg = rg;
	}
	public float getTarifa( ) {
		return 0;
		
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}


}
