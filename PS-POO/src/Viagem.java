// Christian Trisotto Alegri

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Viagem implements  Serializable {

	private String placaOnibus;
	private String nomeMotorista;
	private LocalDate dataViagem;
	private LocalTime horaViagem;
	private ArrayList<Passageiro> passg = new ArrayList<>();

	private boolean isMuni = false;
	
	
	public void addPassageiro(Passageiro p) {
		passg.add(p);
		
	}
	public float getValorTotal() {
		float total = 0;
		for (Passageiro p : passg) {
				if (p.getTipo()=="P") {
					total+=p.getTarifaInteira();	
			}	if (p.getTipo()=="E") {
					total+=(p.getTarifaInteira()/2);
			} 	if (p.getTipo()=="I") {
					total+=(p.getTarifaInteira()*0);
			}
		}
		return total;
		}
	
	
	public Viagem(String placaOnibus, String nomeMotorista, LocalDate dataViagem, LocalTime horaViagem) {
		super();
		this.placaOnibus = placaOnibus;
		this.nomeMotorista = nomeMotorista;
		this.dataViagem = dataViagem;
		this.horaViagem = horaViagem;
	}

	@Override
	public String toString() {
		return "Viagem [placaOnibus=" + placaOnibus + "]";
	}
	public String getPlacaOnibus() {
		return placaOnibus;
	}
	public void setPlacaOnibus(String placaOnibus) {
		this.placaOnibus = placaOnibus;
	}
	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public LocalDate getDataViagem() {
		return dataViagem;
	}
	public void setDataViagem(LocalDate dataViagem) {
		this.dataViagem = dataViagem;
	}
	public LocalTime getHoraViagem() {
		return horaViagem;
	}
	public void setHoraViagem(LocalTime horaViagem) {
		this.horaViagem = horaViagem;
	}
	public ArrayList<Passageiro> getPassg() {
		return passg;
	}
	public void setPassg(ArrayList<Passageiro> passg) {
		this.passg = passg;
	}
	public Passageiro getMaisVelho() {
		if (this.passg.isEmpty()) {
			return null;
		}
		Passageiro velho = this.passg.get(0);
		for (Passageiro pass: this.passg) {
			if (pass.getIdade() > velho.getIdade()) {
				velho = pass;
			}
		}
		return velho;
			}

	public boolean isMuni() {
		return isMuni;
	}
	public void setMuni(boolean isMuni) {
		this.isMuni = isMuni;
	}
	
	public String getOcios() {
		int ocios = 0;
		if (isMuni==true) {
			ocios = 55 - passg.size();
		}
		else {
			ocios = 20 - passg.size();
		}
			return "Ociosidade: "+ocios+ "\nNúmero de passageiros: "+passg.size();
	}
	
	
	public void gerarArquivoCSV() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("viagem.csv");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fos);
			if (isMuni==true) {
			pw.println("Municipal,"+getDataViagem()+","+getHoraViagem()+","+getPlacaOnibus()+","+getNomeMotorista());
			for (Passageiro p : passg) {
				if (p.getTipo()=="P") {
					pw.println(p.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade());
				} else if (p.getTipo()=="E") {
					Estudante e = (Estudante)p;
					pw.println(e.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade()+","+e.getEscola());
				}	 else if (p.getTipo()=="I") {
					Idoso i = (Idoso)p;
					pw.println(p.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade()+","+i.getRg());
				}
			}
		} else {
			pw.println("Intermunicipal,"+getDataViagem()+","+getHoraViagem()+","+getPlacaOnibus()+","+getNomeMotorista());
			for (Passageiro p : passg) {
				if (p.getTipo()=="P") {
					pw.println(p.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade());
				} else if (p.getTipo()=="E") {
					Estudante e = (Estudante)p;
					pw.println(e.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade()+","+e.getEscola());
				}	 else if (p.getTipo()=="I") {
					Idoso i = (Idoso)p;
					pw.println(p.getTipo()+","+p.getNome()+","+p.getTelefone()+","+p.getIdade()+","+i.getRg());
				}
			}
		}
		pw.close();

		
	}

}