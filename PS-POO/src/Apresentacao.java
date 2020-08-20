// Christian Trisotto Alegri

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Apresentacao extends JFrame {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField TFplacaOnibus;
	private JTextField TFnomeMotorista;
	private JTextField TFdataViagem;
	private JTextField TFhoraViagem;
	private JTextField TFnome;
	private JTextField TFidade;
	private JTextField TFrgidoso;
	private JTextField TFescola;
	private JTextField TFBusca;
	private JCheckBox chckbxidoso;
	private final String teste = "viagens.dat";
	private JComboBox<Viagem> CBselecViagem;
	private JRadioButton rdbtnMunicipal;
	private JRadioButton rdbtnIntermunicipal;
	private ArrayList<Passageiro> passageiro = new ArrayList<>();
	private ArrayList<Viagem> viagem = new ArrayList<>();
	private Empresa em = new Empresa();
	private JTextField TFtelefone;
	private DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH mm");
	private JComboBox CBselecTipoDePesquisa;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apresentacao frame = new Apresentacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Apresentacao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Cadastrar Viagem", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblPlacaOnibus = new JLabel("Placa do \u00D4nibus:");
		lblPlacaOnibus.setBounds(28, 9, 89, 14);
		panel.add(lblPlacaOnibus);
		
		TFplacaOnibus = new JTextField();
		TFplacaOnibus.setBounds(154, 9, 86, 20);
		TFplacaOnibus.setColumns(10);
		panel.add(TFplacaOnibus);
		
		JButton ButtonCadViagem = new JButton("Cadastrar");
		ButtonCadViagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				if (rdbtnMunicipal.isSelected()) {
				Viagem v = new Municipal(TFplacaOnibus.getText(), TFnomeMotorista.getText(), LocalDate.parse(TFdataViagem.getText(), formatterData), LocalTime.parse(TFhoraViagem.getText(), formatterHora));
				v.setMuni(true);
				em.addViagens(v);
				viagem.add(v);
				CBselecViagem.addItem(v);
				
			}
			else {
				Viagem v = new Intermunicipal(TFplacaOnibus.getText(), TFnomeMotorista.getText(), LocalDate.parse(TFdataViagem.getText(), formatterData), LocalTime.parse(TFhoraViagem.getText(), formatterHora));
				em.addViagens(v);
				viagem.add(v);
				CBselecViagem.addItem(v);
				}}
			catch(Exception ex) {
				System.out.println("Formato da data: dd/mm/yyyy\nFormato da hora: HH mm");
			}
			
			}});
		ButtonCadViagem.setBounds(90, 186, 89, 23);
		panel.add(ButtonCadViagem);
		
		rdbtnMunicipal = new JRadioButton("Municipal");
		rdbtnMunicipal.setBounds(28, 143, 109, 23);
		panel.add(rdbtnMunicipal);
		
		rdbtnIntermunicipal = new JRadioButton("Intermunicipal");
		rdbtnIntermunicipal.setBounds(133, 143, 109, 23);
		panel.add(rdbtnIntermunicipal);
		
		JLabel lblNomeMotorista = new JLabel("Nome do Motorista:");
		lblNomeMotorista.setBounds(28, 42, 109, 14);
		panel.add(lblNomeMotorista);
		
		TFnomeMotorista = new JTextField();
		TFnomeMotorista.setBounds(154, 42, 122, 20);
		panel.add(TFnomeMotorista);
		TFnomeMotorista.setColumns(10);
		
		JLabel lblDataViagem = new JLabel("Data da Viagem:");
		lblDataViagem.setBounds(28, 74, 89, 14);
		panel.add(lblDataViagem);
		
		JLabel lblHoraViagem = new JLabel("Hora da Viagem:");
		lblHoraViagem.setBounds(28, 113, 89, 14);
		panel.add(lblHoraViagem);
		
		TFdataViagem = new JTextField();
		TFdataViagem.setBounds(154, 73, 86, 20);
		panel.add(TFdataViagem);
		TFdataViagem.setColumns(10);
		
		TFhoraViagem = new JTextField();
		TFhoraViagem.setBounds(154, 113, 86, 20);
		panel.add(TFhoraViagem);
		TFhoraViagem.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Cadastrar Passageiros", null, panel_1, null);
		panel_1.setLayout(null);
		
		CBselecViagem = new JComboBox<Viagem>();
		CBselecViagem.setBounds(161, 11, 200, 22);
		panel_1.add(CBselecViagem);
		
		JLabel SelecViagem = new JLabel("Selecione a viagem:");
		SelecViagem.setBounds(10, 15, 111, 14);
		panel_1.add(SelecViagem);
		
		JLabel lblNomePassageiro = new JLabel("Nome do Passageiro:");
		lblNomePassageiro.setBounds(10, 62, 122, 14);
		panel_1.add(lblNomePassageiro);
		
		TFnome = new JTextField();
		TFnome.setBounds(142, 59, 179, 20);
		panel_1.add(TFnome);
		TFnome.setColumns(10);
		
		JLabel lblidade = new JLabel("Idade:");
		lblidade.setBounds(10, 117, 67, 14);
		panel_1.add(lblidade);
		
		TFidade = new JTextField();
		TFidade.setBounds(87, 111, 53, 20);
		panel_1.add(TFidade);
		TFidade.setColumns(10);
		

		
		JButton btnCadastrarPassageiro = new JButton("Cadastrar");
		btnCadastrarPassageiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Viagem v1 = (Viagem)CBselecViagem.getSelectedItem();
					if (rdbtnMunicipal.isSelected()) {
						if (passageiro.size()==50) {
							throw new IndexOutOfBoundsException("Ônibus lotado!");
						}
					else {
					if (chckbxidoso.isSelected()) {
					if (Integer.parseInt(TFidade.getText())<60) {
				throw new IndexOutOfBoundsException(Integer.parseInt(TFidade.getText()) + " anos não é considerado idoso (Estatuto do idoso: 60 anos ou mais).");
					} else {
					Passageiro p = new Idoso(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()), TFrgidoso.getText());
					passageiro.add(p);
					p.setTipo("I");
					v1.addPassageiro(p);
					
				}
					}
					else if (TFescola.getText().isEmpty()==false && chckbxidoso.isSelected()==false){
						Passageiro p = new Estudante(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()), TFescola.getText());
						passageiro.add(p);	
						p.setTipo("E");
						v1.addPassageiro(p);
						
						
						
					}	else 
					{	Passageiro p = new Passageiro(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()));
						passageiro.add(p);	
						p.setTipo("P");
						v1.addPassageiro(p);
						
					}}}  
					
					else if (rdbtnIntermunicipal.isSelected()) {
						if (passageiro.size() == 20) {
							throw new IndexOutOfBoundsException("Ônibus lotado!");
						}
						else {
						if (chckbxidoso.isSelected()) {
						if (Integer.parseInt(TFidade.getText())<60) {
					throw new IndexOutOfBoundsException(Integer.parseInt(TFidade.getText()) + " anos não é considerado idoso (Estatuto do idoso: 60 anos ou mais).");
						} else {
						Passageiro p = new Idoso(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()), TFrgidoso.getText());
						passageiro.add(p);
						p.setTipo("I");
						v1.addPassageiro(p);
					}
						} 	else if (TFescola.getText().isEmpty()==false && chckbxidoso.isSelected()==false){
							Passageiro p = new Estudante(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()), TFescola.getText());
							passageiro.add(p);		
							p.setTipo("E");
							v1.addPassageiro(p);
						}	else 
						{	Passageiro p = new Passageiro(TFnome.getText(), TFtelefone.getText(), Integer.parseInt(TFidade.getText()));
							passageiro.add(p);
							p.setTipo("P");
							v1.addPassageiro(p);
					}}}
				
			}});
		btnCadastrarPassageiro.setBounds(120, 199, 89, 23);
		panel_1.add(btnCadastrarPassageiro);
		
		TFrgidoso = new JTextField();
		TFrgidoso.setBounds(88, 139, 121, 20);
		panel_1.add(TFrgidoso);
		TFrgidoso.setColumns(10);
		
		JLabel lblRGidoso = new JLabel("RG:");
		lblRGidoso.setBounds(10, 142, 46, 14);
		panel_1.add(lblRGidoso);
		
		JLabel lblEscola = new JLabel("Escola:");
		lblEscola.setBounds(223, 166, 46, 14);
		panel_1.add(lblEscola);
		
		TFescola = new JTextField();
		TFescola.setBounds(266, 163, 139, 20);
		panel_1.add(TFescola);
		TFescola.setColumns(10);
		
		chckbxidoso = new JCheckBox("idoso");
		chckbxidoso.setBounds(24, 179, 97, 23);
		panel_1.add(chckbxidoso);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 92, 67, 14);
		panel_1.add(lblTelefone);
		
		TFtelefone = new JTextField();
		TFtelefone.setBounds(87, 87, 122, 20);
		panel_1.add(TFtelefone);
		TFtelefone.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Utilidades", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String formaPesquisa = (String) CBselecTipoDePesquisa.getSelectedItem();
				String pes = null;
				System.out.println("s");
				if(formaPesquisa == "Data") {
					pes = em.Busca(TFBusca.getText());
					JOptionPane.showMessageDialog(frame, pes);
				}else if(formaPesquisa == "Hora") {
					pes = em.BuscaHora(TFBusca.getText());			
					JOptionPane.showMessageDialog(frame, pes);
				}else if(formaPesquisa == "Placa do Ônibus") {
					pes = em.BuscaPlaca(TFBusca.getText());
					JOptionPane.showMessageDialog(frame, pes);
				}
			}
		
			
			});
		btnPesquisar.setBounds(21, 74, 89, 23);
		panel_2.add(btnPesquisar);
		
		CBselecTipoDePesquisa = new JComboBox();
		CBselecTipoDePesquisa.setBounds(125, 11, 140, 22);
		CBselecTipoDePesquisa.setModel(new DefaultComboBoxModel(new String[] {"Data", "Hora","Placa do Ônibus"}));
		panel_2.add(CBselecTipoDePesquisa);
		
		JLabel lblTipoDePesquisa = new JLabel("Tipo de Pesquisa:");
		lblTipoDePesquisa.setBounds(10, 15, 100, 14);
		panel_2.add(lblTipoDePesquisa);
		
		JLabel lblBusca = new JLabel("Buscar por:");
		lblBusca.setBounds(10, 49, 71, 14);
		panel_2.add(lblBusca);
		
		TFBusca = new JTextField();
		TFBusca.setBounds(91, 44, 86, 20);
		panel_2.add(TFBusca);
		TFBusca.setColumns(10);
		
		JButton btnPassageirosMaisVelhos = new JButton("Lista de passageiros por idade"); 
		btnPassageirosMaisVelhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = "";
				
				ArrayList<Passageiro> velhos = em.getPassageirosMaisVelhos();
		        for (Passageiro p : velhos) {
		        	t += "Lista dos passageiros mais velhos por ordem alfabética:\n Nome:"
		        			+ "                                    Idade:\n"+p.getNome()+""
		        					+ "                  |                  "+p.getIdade()+"\n";
				}	JOptionPane.showMessageDialog(frame, t);
				
			}
			});
		btnPassageirosMaisVelhos.setBounds(222, 139, 197, 23);
		panel_2.add(btnPassageirosMaisVelhos);
		
		JButton btnGerarcsvDaViagem = new JButton("Gerar CSV da Viagem");
		btnGerarcsvDaViagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				em.gerarArquivoCSV();
			}
			
			});
		btnGerarcsvDaViagem.setBounds(241, 105, 133, 23);
		panel_2.add(btnGerarcsvDaViagem);
		
		JButton btnValorArrecadado = new JButton("Valor Arrecadado");
		btnValorArrecadado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				JOptionPane.showMessageDialog(frame, em.getTotal());
			}
			
			});
		btnValorArrecadado.setBounds(232, 175, 133, 23);
		panel_2.add(btnValorArrecadado);
		
		JButton btnNpassageirosOciosidade = new JButton("N\u00FAmero de passageiros + Ociosidade");
		btnNpassageirosOciosidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, em.getOciosidade());
			}
			});
		btnNpassageirosOciosidade.setBounds(175, 209, 230, 23);
		panel_2.add(btnNpassageirosOciosidade);
		
		JButton btnSalvar = new JButton("Salvar Viagens");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ObjectOutputStream salvaViagens = new ObjectOutputStream(new FileOutputStream(teste));
					salvaViagens.writeObject(viagem);
					salvaViagens.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			});
		btnSalvar.setBounds(21, 123, 133, 23);
		panel_2.add(btnSalvar);
		
		JButton btnRecuperar = new JButton("Recuperar Viagens");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
            	int returnVal = jfc.showOpenDialog(null);
            	if(returnVal == JFileChooser.APPROVE_OPTION) {
            		try {
						System.out.println("teste");
            			ObjectInputStream recupera = new ObjectInputStream(new FileInputStream(teste));
						viagem = (ArrayList<Viagem>) recupera.readObject();	
						for (Viagem v : viagem) {
							CBselecViagem.addItem(v);
						}
						recupera.close();						
            		} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		
            	}
            	
			}
		});
		btnRecuperar.setBounds(21, 175, 133, 23);
		panel_2.add(btnRecuperar);
	}}

