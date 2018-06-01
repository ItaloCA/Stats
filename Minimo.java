class Minimo extends Operacoes{
	LerArquivo dados;

	Minimo(LerArquivo dados){
		this.dados = dados;
	}

	double calcular(int coluna){
		double minimo = 999999999.9999;
		int tam = dados.getNumLinhas();
		for (int i = 0; i < tam; i++) {
			double x = dados.converteNumerico(i, coluna);
			
			if(x < minimo) 
				minimo = x;
		}

		return minimo;
	}
}