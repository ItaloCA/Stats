import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;



class Graficos{



	public String histograma(int coluna, CalcMetricas csv){
		HistogramDataset dataset = new HistogramDataset();
		String titulo = csv.titulo[coluna];
		double[] vetor = new double[csv.getNumLinhas];
		for (int i=0; i < csv.getNumLinhas(); i++) {
			vetor[i] = csv.converteNumerico(i, coluna);
		}

		dataset.addSeries("Histograma", vetor, 10);

		PlotOrientation orientacao = PlotOrientation.VERTICAL;
		JFreeChart grafico = ChartFactory.createHistogram( "Histograma", "Numero", "Valor", 
                dataset, orientacao, false, false, false);

     	int largura = 500;
       	int altura = 500;

		try {
        	ChartUtilities.saveChartAsPNG(new File("histograma.PNG"), grafico, largura, altura);
        } catch (IOException e) {}

		/*public static JFreeChart createHistogram(String title,
                                         String xAxisLabel,
                                         String yAxisLabel,
                                         IntervalXYDataset dataset)



		public static JFreeChart createHistogram(String title,
                                         String xAxisLabel,
                                         String yAxisLabel,
                                         IntervalXYDataset dataset,
                                         PlotOrientation orientation,
                                         boolean legend,
                                         boolean tooltips,
                                         boolean urls)

		*/

	return "histograma.PNG";
	}
	

	JTable distrFrequencia(int coluna, CalcMetricas csv){
		String elemento = null;
		Map<String, Integer> frequencias_simples = new HashMap<String, Integer>();

		for (int i=0; i < (this.getNumLinhas()); i++) {
			elemento = this.getElemento(i, coluna);

			if(frequencias_simples.containsKey(elemento)){
				frequencias_simples.put(elemento, frequencias_simples.get(elemento));
			}
			else {
				frequencias_simples.put(elemento, 1);
			}
		}
		String[][] distrFrequencia = new String[frequencias_simples.size() +1][2];

		Set<String> chaves = new HashSet<String>(frequencias_simples.keySet());
		Iterator<String> iterator = chaves.iterator();
		int i = 1;
		while (iterator.hasNext()){
            elemento = iterator.next();
			distrFrequencia[i][0] = elemento;
			distrFrequencia[i][1] = frequencias_simples.get(elemento);

		}	 	
		JTable tabela = new JTable(distrFrequencia, {"X", "X = xi"}); 

		return tabela;

		
	}
}