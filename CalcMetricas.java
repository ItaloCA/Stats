import java.io.File;
import java.util.Arrays;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CalcMetricas extends CSV{

	Map<String, Double> resultados = new HashMap<String, Double>();

	public static void main(String[] args) {
			File file = new File("arq.csv");
			CalcMetricas csv = new CalcMetricas(file);

			for (int i=0; i < csv.getNumLinhas(); i++) {
				System.out.println(csv.getElemento(i,3));
				
			}
			System.out.println("  media:" + csv.media(3));
			System.out.println("  moda:" + csv.moda(3));
			System.out.println("  moda:" + csv.moda(2));
			System.out.println("  mediana:" + csv.mediana(3));
			System.out.println("  variancia:" + csv.variancia(3));
			System.out.println("  maximo:" + csv.maximo(3));
			System.out.println("  minimo:" + csv.minimo(3));
			


		}	
	public CalcMetricas (File arquivoCSV){
		super.run(arquivoCSV);
	}
	public int getNumLinhas(){
		return this.numLinhas;
	}
	public int getNumColunas(){
		return this.numColunas;
	}
	
	public boolean colNumerica(int coluna){
		int linha = 0;
		String elemento=null;
		try{
			elemento =  this.getElemento(linha,coluna);
			while(elemento.equals("")){
				linha++;
				elemento=this.getElemento(linha,coluna);//percorre a coluna,descendo linha a linha até achar algo não vazio
			}
		}catch(Exception e){
			System.out.println("CSV com problema de leitura.");
		}
		//quando o elemento é achado este é comparado e ocorre verificação se é ou não numerico
		if(elemento.matches("^([+-]?\\d*\\.?\\d*)$")){
			return true;
		}else{
			return false;
		}
		
	}
	public double converteNumerico(int linha,int coluna){
		return Double.parseDouble(this.getElemento(linha, coluna));
	}
	
	public String [] colsNumericas(){
		int numeroColunas = this.getNumColunas();
		String [] Numericas = new String [numeroColunas];
		int cont = 0;
		for(int i=0;i<numeroColunas;i++){
			if(this.colNumerica(i)){
				Numericas[cont]=this.titulo[i];   //(titulo) atributo que guarda a primeira linha(0) do csv ,assim passa para o vetor o nome da coluna 
				cont++;
			}
		}
		return Numericas;
		
	} 
/*
	public double getResult(String key){
		return this.resultados.get(key);
	}
//*/
///////////////////////////-------------------------------------------------- Calculos -----------------------------------------------------------/////////////////////////////////////////////////	
	
	double media(int coluna){
		double media = 0;
		for (int i=0; i < (this.getNumLinhas()); i++) {
			media = media + this.converteNumerico(i, coluna);
		}
		media = media/(this.getNumLinhas());		 
		return media;
	}
	double mediana(int coluna){
		double mediana = 0;
		int tam = this.getNumLinhas();
		double[] lista = new  double[tam];
		for (int i = 0; i < tam; i++) {
			lista[i] = this.converteNumerico(i, coluna);			
		}
		
		Arrays.sort(lista);

		if ((tam % 2) == 0) {
			double x = lista[tam/2];
			double y = lista[(tam/2)+1];
			mediana = (x+y)/2;
		}else {
			mediana = lista[(tam/2) + 1];
		}

		return mediana;
	}


	String moda(int coluna){
		String moda = null;
		int veses = 0;
		String elemento = null;
		Map<String, Integer> frequencias_simples = new HashMap<String, Integer>();

		for (int i=0; i < (this.getNumLinhas()); i++) {
			elemento = this.getElemento(i, coluna);

			if(frequencias_simples.containsKey(elemento)){
				frequencias_simples.put(elemento, frequencias_simples.get(elemento) + 1);
			}
			else {
				frequencias_simples.put(elemento, 1);
			}
		}

		Set<String> chaves = new HashSet(frequencias_simples.keySet());
		Iterator<String> iterator = chaves.iterator();

		while (iterator.hasNext()){
            elemento = iterator.next();
			if(frequencias_simples.get(elemento) > veses) {
				moda = elemento;
				veses = frequencias_simples.get(elemento);
			}	 	
		}		 
		return moda;
	}


	double variancia(int coluna){
		double media = media(coluna);
		double x;
		double variancia = 0;
		for (int i=0; i < (this.getNumLinhas()); i++) {
			x = this.converteNumerico(i, coluna);
			variancia = variancia + (x*x);
		}
		variancia = variancia/(this.getNumLinhas());
		variancia = variancia - media*media;		 
		return variancia;
	}


	double maximo(int coluna){
		double maximo = -999999.9999;
		int tam = this.getNumLinhas();
		for (int i = 0; i < tam; i++) {
			double x = this.converteNumerico(i, coluna);
			
			if(x > maximo) 
				maximo = x;
		}

		return maximo;
	}


	double minimo (int coluna){
		double minimo = 999999999.9999;
		int tam = this.getNumLinhas();
		for (int i = 0; i < tam; i++) {
			double x = this.converteNumerico(i, coluna);
			
			if(x < minimo) 
				minimo = x;
		}

		return minimo;
	}








}