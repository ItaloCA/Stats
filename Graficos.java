import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.io.File;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.data.statistics.BoxAndWhiskerCalculator;
//import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
//import org.jfree.data.statistics.DefaultBoxAndWhiskerXYDataset;
import org.jfree.date.DateUtilities;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 



class Graficos{



	public File histograma(int coluna, CalcMetricas csv){
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

       	File graficoimg;
		try {
        	ChartUtilities.saveChartAsPNG(graficoimg, grafico, largura, altura);
        } catch (Exception e) {}

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

	return graficoimg;
	}
	

	JTable distrFrequencia(int coluna, CalcMetricas csv){
		String elemento = null;
		Map<String, Integer> frequencias_simples = new HashMap<String, Integer>();

		for (int i=0; i < (csv.getNumLinhas()); i++) {
			elemento = csv.getElemento(i, coluna);

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
		String[] titulo = new String[2];
		titulo[0] = "X";
		titulo[1] = "X = xi";


		JTable tabela = new JTable(distrFrequencia, titulo); 

		return tabela;

		
	}

	File boxPlot(int coluna, CalcMetricas csv){
		DefaultBoxAndWhiskerXYDataset dataset = new DefaultBoxAndWhiskerXYDataset("BoxPlot");
		
		
 		Date date = DateUtilities.createDate(2018, 6, 5, 12, 0);

 		csv.calcular(coluna);

 		double media = csv.resultados.get("media");
 		double mediana = csv.resultados.get("mediana");
 		double media = csv.resultados.get("media");
 		double minimo = csv.resultados.get("minimo");
 		double maximo = csv.resultados.get("maximo");
 		double desvioPadrao = csv.resultados.get("desvio padrao");

 		int tam = csv.getNumLinhas();
 		double[] lista = new  double[tam];
		for (int i = 0; i < tam; i++) {
			lista[i] = csv.converteNumerico(i, coluna);			
		}
		
		Arrays.sort(lista);

		ArrayList<String> outliers = new ArrayList<>();

		for (int i = 0; i < tam; i++) {
			if(csv.converteNumerico(i, coluna) < desvioPadrao || csv.converteNumerico(i, coluna) > desvioPadrao)
				outliers.add(csv.converteNumerico(i,coluna));
		}

 		BoxAndWhiskerItem item = new BoxAndWhiskerItem(double media,
                         	double mediana,
                        	lista[tam/4],
                        	lista[3*(tam/4)],
                        	minimo,
                       	 	maximo,
                     	  	desvioPadrao,
                         	desvioPadrao,
                         	outliers);

 		/*public BoxAndWhiskerItem(double mean,
                         double median,
                         double q1,
                         double q3,
                         double minRegularValue,
                         double maxRegularValue,
                         double minOutlier,
                         double maxOutlier,
                         List outliers);
		//*/

		dataset.add(date, item);


		JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("Box and Whisker Chart", "Time", "Value", dataset, true);
 		chart.setBackgroundPaint(new Color(210, 30, 0));

		int largura = 500;
       	int altura = 500;

       	File graficoimg;
		try {
        	ChartUtilities.saveChartAsPNG(graficoimg, chart, largura, altura);
        } catch (Exception e) { System.out.println("Graficos: linha 163: ERRO");}

        return graficoimg;
	}


	File scatter(int X, int Y, CalcMetricas csv){
		XYDataset dataset = new XYSeriesCollection();
		 
		XYSeries series = new XYSeries(csv.titulos[X]);

		for (int i = 0; i< csv.getNumLinhas(); i++) {
			series.add(csv.converteNumerico(i, X), csv.converteNumerico(i, Y));
		}

		dataset.addSeries(series);


		JFreeChart grafico = ChartFactory.createScatterPlot("Scatter", csv.titulos[X], csv.titulos[Y], dataset);


		XYPlot plot = (XYPlot)grafico.getPlot();
    	plot.setBackgroundPaint(new Color(210,30,0));


    	int largura = 500;
       	int altura = 500;

       	File graficoimg;
		try {
        	ChartUtilities.saveChartAsPNG(graficoimg, grafico, largura, altura);
        } catch (Exception e) { System.out.println("Graficos: linha 214: ERRO");}

        return graficoimg;
	}


	public File gBarras(int coluna, CalcMetricas csv){
		BarChart_AWT chart = new BarChart_AWT(csv.titulos[coluna], 
         "Which car do you like?");


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

       	File graficoimg;
		try {
        	ChartUtilities.saveChartAsPNG(graficoimg, grafico, largura, altura);
        } catch (Exception e) {}

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

	return graficoimg;
	}


}