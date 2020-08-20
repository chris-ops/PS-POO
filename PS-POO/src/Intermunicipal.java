// Christian Trisotto Alegri

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Intermunicipal extends Viagem {
	private ArrayList<Passageiro> inter = new ArrayList<>();
	
	public Intermunicipal(String placaOnibus, String nomeMotorista, LocalDate dataViagem, LocalTime horaViagem) {
		super(placaOnibus, nomeMotorista, dataViagem, horaViagem);
		// TODO Auto-generated constructor stub
	}

	public void addPassageiro(Passageiro p) {
		super.addPassageiro(p);					// adicionar passageiro no arraylist passg (na classe viagem)
		inter.add(p);
	}
	public float getValorTotal() {
		float total = 0;
		for (Passageiro p : inter) {						
					System.out.println(inter.size());
					if (p.getTipo()=="P") {		
						total+=(p.getTarifaInteira()+3.15);	
				}	if (p.getTipo()=="E") {
						total+=(p.getTarifaInteira()/2)+3.15;
				} 	if (p.getTipo()=="I") {
						total+=(p.getTarifaInteira()*0)+3.15;
				}
				
				
		
			
		}
		return total;
		
	
}

}
