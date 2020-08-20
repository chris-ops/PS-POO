import java.io.Serializable;

public class Estudante extends Passageiro implements Serializable{
	
	private String escola;

	public Estudante(String nome, String telefone, int idade, String escola) {
		super(nome, telefone, idade);
		this.escola = escola;
	}
	public float getTarifa( ) {
		return 5/2;
		
	}
	public String getEscola() {
		return escola;
	}
	public void setEscola(String escola) {
		this.escola = escola;
	}
	
}
