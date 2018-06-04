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
	
}