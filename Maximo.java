class Maximo extends Operacoes{
	LerArquivo dados;

	Maximo(LerArquivo dados){
		this.dados = dados;
	}

	double calcular(int coluna){
		double maximo = -999999.9999;
		int tam = dados.getNumLinhas();
		for (int i = 0; i < tam; i++) {
			double x = dados.converteNumerico(i, coluna);
			
			if(x > maximo) 
				maximo = x;
		}

		return maximo;
	}
}