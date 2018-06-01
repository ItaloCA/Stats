import java.lang.Math;

class DesvioPadrao extends Operacoes{
	LerArquivo dados;
	Variacia variacia;

	DesvioPadrao(LerArquivo dados, Variancia variacia){
		this.dados = dados;
		this.variacia = variacia;
	}
	DesvioPadrao(LerArquivo dados){
		this.dados = dados;
		Variancia variacia = new Variancia(dados);
		this.variancia = variacia.calcular();
	}

	double calcular(int coluna){
		double desvio = 0;
		desvio = Math.sqrt(variacia);
		
		return desvio;
	}
}