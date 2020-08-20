// Christian Trisotto Alegri

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Municipal extends Viagem {
	private ArrayList<Passageiro> muni = new ArrayList<>();
	public Municipal(String placaOnibus, String nomeMotorista, LocalDate dataViagem, LocalTime horaViagem) {
		super(placaOnibus, nomeMotorista, dataViagem, horaViagem);
		// TODO Auto-generated constructor stub
	}

	public void addPassageiro(Passageiro p) {
		super.addPassageiro(p);					// adicionar passageiro no arraylist passg (na classe viagem)
		muni.add(p);
	}

	
}
