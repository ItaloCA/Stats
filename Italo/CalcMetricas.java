import java.io.File;
import java.util.Arrays;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CalcMetricas extends CSV{

	Map<String, String> resultados = new HashMap<String, String>();

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
			
			System.out.println("desvioPadrao:" + csv.desvioPadrao(3));

			csv.calcular(3);

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


	void calcular(int coluna){
		if (this.colNumerica(coluna)) {
			double media = media(coluna);
			this.resultados.put("media", Double.toString(media));
			this.resultados.put("mediana", Double.toString(mediana(coluna)));
			this.resultados.put("moda", moda(coluna));
			double variancia = variancia(coluna);
			this.resultados.put("variancia", Double.toString(variancia));
			this.resultados.put("maximo", Double.toString(maximo(coluna)));
			this.resultados.put("minimo", Double.toString(minimo(coluna)));
			this.resultados.put("desvio padrao", Double.toString(desvioPadrao(variancia)));
			this.resultados.put("assimetria", Double.toString(assimetria(coluna, media)));
			this.resultados.put("curtose", Double.toString(curtose(coluna, media)));
			
		}
		else{
			this.resultados.put("moda", moda(coluna));
		}


	}


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



	double desvioPadrao(int coluna){
		double variancia = variancia(coluna);
		return Math.sqrt(variancia);
	}
	double desvioPadrao(double variancia){
		return Math.sqrt(variancia);
	}


	double assimetria(int coluna){
		double media = media(coluna);
		double m3 = 0, s3 = 0, sk, xi;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			m3 = m3 + (xi * xi * xi);
		}
		m3 = m3 / getNumLinhas();

		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			s3 = s3 + (xi * xi);
		}
		s3 = s3 / getNumLinhas();
		s3 = s3*s3*s3;
		s3 = Math.sqrt(s3);

		sk = m3 / s3;
		return sk;
	}

	double assimetria(int coluna, double media){
		double m3 = 0, s3 = 0, sk, xi;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			m3 = m3 + (xi * xi * xi);
		}
		m3 = m3 / getNumLinhas();

		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			s3 = s3 + (xi * xi);
		}
		s3 = s3 / getNumLinhas();
		s3 = s3*s3*s3;
		s3 = Math.sqrt(s3);

		sk = m3 / s3;
		return sk;
	}



	double curtose(int coluna){
		double media = media(coluna);
		double m4 = 0, s4 = 0, k, xi;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			m4 = m4 + (xi * xi * xi * xi);
		}
		m4 = m4 / getNumLinhas();

		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			s4 = s4 + (xi * xi);
		}
		s4 = s4 / getNumLinhas();
		s4 = s4*s4;

		k = m4 / s4;
		return k;
	}

	double curtose(int coluna, double media){
		double m4 = 0, s4 = 0, k, xi;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			m4 = m4 + (xi * xi * xi * xi);
		}
		m4 = m4 / getNumLinhas();

		for (int i = 0; i < getNumLinhas(); i++) {
			xi = (converteNumerico(i, coluna) - media);
			s4 = s4 + (xi * xi);
		}
		s4 = s4 / getNumLinhas();
		s4 = s4*s4;

		k = m4 / s4;
		return k;
	}

/*
	double esperanca(int coluna){
		Map<Double, Double> X = new HashMap<Double, Double>();
		Double xi, esperanca = 0;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = converteNumerico(i, coluna);
			if (X.containsKey(xi)) {
				X.put(xi, X.get(xi) + 1);
			}else{
				X.put(xi, 1);
			}
		}


		Set<Double> chaves = new HashSet(X.keySet());
		Iterator<Double> iterator = chaves.iterator();

		while (iterator.hasNext()){
            xi = iterator.next();
            esperanca += X.get(xi) * xi;
				
		}	
		
		esperanca = esperanca / getNumLinhas();

		return esperanca;

	}

	double esperanca(int colunax, int colunay){
		Map<Double, Double> X = new HashMap<Double, Double>();
		Map<Double, Double> Y = new HashMap<Double, Double>();
		Double xi, yi, esperanca = 0;
		for (int i = 0; i < getNumLinhas(); i++) {
			xi = converteNumerico(i, coluna);
			if (X.containsKey(xi)) {
				X.put(xi, X.get(xi) + 1);
			}else{
				X.put(xi, 1);
			}
		}


		Set<Double> chaves = new HashSet(X.keySet());
		Iterator<Double> iterator = chaves.iterator();

		while (iterator.hasNext()){
            xi = iterator.next();
            esperanca += X.get(xi) * xi;
				
		}	

		esperanca = esperanca / getNumLinhas();
		return esperanca;

	}
//*/
	double covariancia(int X, int Y){
		double x_ = media(X);
		double y_ = media(Y);

		double mediaxy = 0;
		for (int i=0; i < (this.getNumLinhas()); i++) {
			mediaxy = mediaxy + converteNumerico(i, X)*converteNumerico(i, Y);
			
		}
		mediaxy = mediaxy/(this.getNumLinhas());		 

		return mediaxy - (x_*y_);
	}

}