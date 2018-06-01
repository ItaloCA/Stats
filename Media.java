class Media extends Operacoes{
	LerArquivo dados;

	double calcular(int coluna){
		double media = 0;
		for (int i=0; i < (dados.getNumLinhas() - 1); i++) {
			media = media + dados.converteNumerico(i, coluna);
		}
		media = media/(dados.getNumLinhas() -1);		 
		return media;
	}
}