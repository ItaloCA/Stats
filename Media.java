class Media extends Operacoes{
	LerArquivo dados;

	Media(LerArquivo dados){
		this.dados = dados;
	}

	double calcular(int coluna){
		double media = 0;
		for (int i=0; i < (dados.getNumLinhas()); i++) {
			media = media + dados.converteNumerico(i, coluna);
		}
		media = media/(dados.getNumLinhas());		 
		return media;
	}
}