//package stats2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public  class SecondStage extends JFrame implements ActionListener {
	
	//elementos da interface grafica e elementos usados para as operacoes
	private JButton pColuna ;
	private JButton aColuna ;
	private JButton load;
	private int index = 0;
	CalcMetricas csv;
	JPanel painelFundo;
	JTable tabela,table;
	JScrollPane barraRolagem,Sp;

	//construtor da classe
	public SecondStage(CalcMetricas csv){
		super("Staticts");
		this.csv = csv;
	};
	//metodo que mostra as tabelas
	void secondStage(){

		//inicializando botoes
		pColuna = new JButton("Proxima coluna >");
		pColuna.addActionListener(this);
		pColuna.setVisible(true);

		
		aColuna = new JButton(" < Coluna Anterior ");
		aColuna.setVisible(true);
		aColuna.addActionListener(this);
		
		load = new JButton("Carregar");
		load.addActionListener(this);
		load.setVisible(true);
		
		//cria a matriz com as informacoes para o JTable
		Object[][] dados = criarUnivariadas();
		Object[][] data = criarCovariancia();

	    //titulos das colunas
		String [] colunas = {"Univariadas", "Resultado"};
		String [] colums = {"Covariancia"};
		JPanel painelFundo = new JPanel();
		JPanel buttonPanel = new JPanel();


		tabela = new JTable(dados, colunas);

		table = new JTable(data,colums);

		barraRolagem = new JScrollPane(tabela);
		Sp = new JScrollPane(table);
		
		
		
		//formulando o layout
		painelFundo.setLayout(new FlowLayout());
		painelFundo.add(barraRolagem);
		painelFundo.add(Sp);
		painelFundo.add(aColuna);
		painelFundo.add(pColuna);
		painelFundo.add(load);
		
		
		getContentPane().add(painelFundo);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);
		setVisible(true);



	}




	@Override
	//tratamento de eventos
	public void actionPerformed(ActionEvent evento) {
		//caso seja coluna anterior diminui-se o index que diz em qual coluna estamos
		if(evento.getSource() == aColuna){
			index--;
			if(index < 0)
				index = csv.getNumColunas()-1;	
			//refaz a interface
			Object[][]dados =  criarUnivariadas();
			Object[][] data = criarCovariancia();
			String[] colunas = {"Univariadas", "Resultado"};
			String [] colums = {"Covariancia"};
			tabela = new JTable(dados, colunas);
			table = new JTable(data,colums);
			painelFundo = new JPanel();
			painelFundo.setLayout(new FlowLayout());
			barraRolagem = new JScrollPane(tabela);
			Sp = new JScrollPane(table);
			painelFundo.add(barraRolagem);
			painelFundo.add(Sp);
			painelFundo.add(aColuna);
			painelFundo.add(pColuna); 
			painelFundo.add(load);
			pColuna.setVisible(true);
			aColuna.setVisible(true);

			getContentPane().add(painelFundo);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1000, 700);
			setVisible(true);

		}
		//caso seja coluna anterior aumenta-se o index que diz em qual coluna estamos
		if(evento.getSource() == pColuna){
			index++;
			if(index > csv.getNumColunas() - 1)
				index = 0;
			//refaz a interface
			Object[][]dados =  criarUnivariadas();
			Object[][] data = criarCovariancia();
			String[] colunas = {"Univariadas", "Resultado"};
			String [] colums = {"Covariancia"};
			tabela = new JTable(dados, colunas);
			table = new JTable(data,colums);
			painelFundo = new JPanel();
			painelFundo.setLayout(new FlowLayout());
			barraRolagem = new JScrollPane(tabela);
			Sp = new JScrollPane(table);
			painelFundo.add(barraRolagem);
			painelFundo.add(Sp);
			painelFundo.add(aColuna);
			painelFundo.add(pColuna); 
			painelFundo.add(load);
			pColuna.setVisible(true);
			aColuna.setVisible(true);
			load.setVisible(true);
			
			

			getContentPane().add(painelFundo);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1000, 700);
			setVisible(true);
		}
		//carrega outro arquivo
		if(evento.getSource() == load){
			File arquivo = Importador.explorador();
			if(arquivo != null) {
				this.csv = new CalcMetricas(arquivo);
			}
			//refaz a interface
			Object[][]dados =  criarUnivariadas();
			Object[][] data = criarCovariancia();
			String[] colunas = {"Univariadas", "Resultado"};
			String [] colums = {"Covariancia"};
			tabela = new JTable(dados, colunas);
			table = new JTable(data,colums);
			painelFundo = new JPanel();
			painelFundo.setLayout(new FlowLayout());
			barraRolagem = new JScrollPane(tabela);
			Sp = new JScrollPane(table);
			painelFundo.add(barraRolagem);
			painelFundo.add(Sp);
			painelFundo.add(aColuna);
			painelFundo.add(pColuna); 
			painelFundo.add(load);
			pColuna.setVisible(true);
			aColuna.setVisible(true);
			load.setVisible(true);
		
			getContentPane().add(painelFundo);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(1000, 700);
			setVisible(true);
		}
	}
	//cria uma matriz de Object de univariadas
	private Object[][] criarUnivariadas() {
		//caso seja uma coluna numerica
		if (csv.colNumerica(index)) {
			Object [][] dados = {
				{"Media", csv.media(index)},
				{"Moda", csv.moda(index)},
				{"Mediana", csv.mediana(index)},
				{"Minimo", csv.minimo(index)},
				{"Maximo", csv.maximo(index)},
				{"Variancia", csv.variancia(index)},
				{"Desvio Padrao", csv.desvioPadrao(index)},
				{"Skewness", csv.assimetria(index)},
				{"Kurtosis", csv.curtose(index)}
			};

			return dados;
		}
		//caso nao seja
		Object [][] dados = {
			{"Media", " "},
			{"Moda", csv.moda(index)},
			{"Mediana", " "},
			{"Minimo", " "},
			{"Maximo", " "},
			{"Variancia", " "},
			{"Desvio Padrao", ""},
			{"Skewness", ""},
			{"Kurtosis", ""}	
		};
		return dados;

		
	}
	//cria uma Matriz Object com a covariancias do index com as outras colunas 
	private Object[][]criarCovariancia(){
		//caso o index seja uma coluna numerica
		Object[][] data;
		if(csv.colNumerica(index)) {	
			int i = 0,x=0;
			int cont = 0;
			while(i < csv.getNumColunas()) {
				if(csv.colNumerica(i)) {
					cont++;
				}
				i++;
			}
			i = 0;
			
			int[] coluna = new int[cont];
			while(i < csv.getNumColunas()) {
				if(csv.colNumerica(i)) {
					coluna[x] = i;
					x++;
				}i++;
			}
			
			data = new Object[cont][1];
			i = 0;
			while(i < cont) {
				data[i][0] = csv.covariancia(index,coluna[i]);
				i++;
			}
			
			return data;
		}
		//caso nao seja
		else {
			data = new Object[1][1];
			data[0][0] = 0;
			return data;
		}
	}
}