class Mediana extends Operacoes{
	LerArquivo dados;

	Mediana(LerArquivo dados){
		this.dados = dados;
	}

	double calcular(int coluna){
		double mediana = 0;
		int tam = dados.getNumLinhas();
		if ((tam % 2) == 0) {
			double x = dados.converteNumerico(tam/2, coluna);
			double y = dados.converteNumerico((tam/2)+1, coluna);
			mediana = (x+y)/2
		}else {
			mediana = dados.converteNumerico((tam/2) + 1, coluna);
		}

		return mediana;
	}
}