import java.io.File;
import java.util.Arrays;

public class CalcMetricas.java extends tpf{
	
	public CalcMetricas (String arquivoCSV){
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
		try{
			String elemento =  this.getElemento(linha,coluna);
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
		int cont;
		for(int i=0;i<numeroColunas;i++){
			if(this.colNumerica(i)){
				Numericas[cont]=this.titulo[i];//(titulo) atributo que guarda a primeira linha(0) do csv ,assim passa para o vetor o nome da coluna 
				cont++;
			}
		}
		return Numericas;
		
	} 
}