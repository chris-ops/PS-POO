import java.io.Serializable;
import java.util.HashMap;

// Christian Trisotto Alegri

public class Passageiro implements Serializable{
	private String nome;
	private String telefone;
	private int idade;
	private float tarifaInteira = 5; //type mismatch se 5.0 (double)?
	private String tipo;

	
	public Passageiro(String nome, String telefone, int idade) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
	}






	public float getTarifaInteira() {
		return tarifaInteira;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	public void setTarifaInteira(float tarifaInteira) {
		this.tarifaInteira = tarifaInteira;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
