import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Moda extends Operacoes{
	LerArquivo dados;

	Moda(LerArquivo dados){
		this.dados = dados;
	}

	double calcular(int coluna){
		double moda = 0;
		int veses = 0;
		double elemento;
		Map<Double, Integer> frequencias_simples = new HashMap<Double, Integer>();

		for (int i=0; i < (dados.getNumLinhas()); i++) {
			elemento = dados.converteNumerico(i, coluna);

			if(frequencias_simples.containsKey(elemento)){
				frequencias_simples.put(elemento, 1);
			}
			else {
				frequencias_simples.put(elemento, frequencias_simples.get(elemento) + 1);
			}
		}

		Set<Double> chaves = new HashSet(frequencias_simples.keySet());
		Iterator<Double> iterator = chaves.iterator();

		while (iterator.hasNext()){
            elemento = iterator.next();
			if(frequencias_simples.get(elemento) > veses) {
				moda = elemento;
				veses = frequencias_simples.get(moda);
			}	 	
		}		 
		return moda;
	}
}