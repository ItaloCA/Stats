class Variancia extends Operacoes{
	LerArquivo dados;
	double media;

	Variancia(LerArquivo dados, double media){
		this.dados = dados;
		this.media = media;
	}
	Variancia(LerArquivo dados){
		this.dados = dados;
		Media media = new Media(dados);

		this.media = media.calcular(); 
	}

	double calcular(int coluna){

		double x;
		double variancia = 0;
		for (int i=0; i < (dados.getNumLinhas()); i++) {
			x = dados.converteNumerico(i, coluna);
			variancia = variancia + (x*x);
		}
		variancia = variancia/(dados.getNumLinhas());
		variancia = variancia - media*media;		 
		return variancia;
	}
}