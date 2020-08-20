// Christian Trisotto Alegri

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class Empresa {

	private HashMap<String, Viagem> viagens = new HashMap<>();
	ArrayList<Viagem> via = new ArrayList<>(); // para especificar a viagem e pegar o valor arrecadado
	
	    public ArrayList<Passageiro> getPassageirosMaisVelhos() {
	        ArrayList<Passageiro> velhos = new ArrayList();
	        for (Viagem v : viagens.values()) {
	            String nome = v.getMaisVelho().getNome();
	            String telefone = v.getMaisVelho().getTelefone();
	            int idade = v.getMaisVelho().getIdade();
	            Passageiro p = new Passageiro(nome, telefone, idade);   
	            velhos.add(p);

	        }
			Collections.sort(velhos, new Comparator<Passageiro>() {
			    public int compare(Passageiro v1, Passageiro v2) {
			        return v1.getNome().compareTo(v2.getNome());
			    }
			});
			
	        
			return velhos;
	    }
	
	public void addViagens(Viagem v) {
		if(v == null) {
			throw new IllegalArgumentException("A viagem não pode ser nula!");
		}
		if(viagens.containsKey(v.getPlacaOnibus())) {
			throw new IllegalArgumentException("Esse ônibus já está em viagem!");
		}
		viagens.put(v.getPlacaOnibus(), v);
		System.out.println("Viagem cadastrada!");

	}
	
	public String Busca(String Data) {
		String str = "";
		for(Viagem v : viagens.values()) {
			if(Data.equals(v.getDataViagem().toString())) {
				str = "Viagem encontrada!";
											// limpa o arraylist para evitar que múltiplas buscas acumulem (na hora de calcular n de passageiros e ociosidade, por ex.).
				via.add(0, v); 							// coloca a viagem especificada no index 0
			}
		}
		return str;
}
	
	
	public String BuscaHora(String Hora) {
		String str = "";
		for(Viagem v : viagens.values()) {
			if(Hora.equals(v.getHoraViagem().toString())) {
				str = "Viagem encontrada!";
											// limpa o arraylist para evitar que múltiplas buscas acumulem (na hora de calcular n de passageiros e ociosidade, por ex.).
				via.add(0, v);							// coloca a viagem especificada no index 0
			}
		}
		return str;
}
	public String BuscaPlaca(String Placa) {
		String str = "";
		for(Viagem v : viagens.values()) {
			if(Placa.equals(v.getPlacaOnibus())) {
				str = "Viagem encontrada!";
				System.out.println("s");
											// limpa o arraylist para evitar que múltiplas buscas acumulem (na hora de calcular n de passageiros e ociosidade, por ex.).
				via.add(0, v);							// coloca a viagem especificada no index 0
				
			}
		}
		return str;
}
	public float getTotal() {
	float total = 0;
	total = via.get(0).getValorTotal();
		return total;
	}
	public String getOciosidade() {
	return via.get(0).getOcios();
	}
	public void gerarArquivoCSV() {
		try {
		via.get(0).gerarArquivoCSV();
	} catch(IndexOutOfBoundsException i){ 
		System.out.println("Pesquise por uma viagem antes!");
	}}


	}
