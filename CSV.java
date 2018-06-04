import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*--------------------------------------------------------------------------------
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;

//javac -classpath jfreechart-1.0.19/*.jar CSV.java   ----->  escrever no terminal
//*/
public class CSV {
      ArrayList<String[]> dados = new ArrayList <>(); //armazena dados do csv por meio de ArrayList
      int numColunas;
      int numLinhas;
      String [] titulo;
      /* public static void main(String[] args) {
    
        tpf obj = new tpf();
        obj.run("netflix.csv");
        obj.toString();
        obj.teste();   
        obj.convert();
    
      }*/ 
	     //main 

      public CSV() {}

      public CSV(String caminho){
        File arq = new File(caminho);
        run(arq);
      }

      public void run(File csv) {
    //File csv = new File(arquivoCSV);
        try{
        //guardar o cabecalho que sao os titulos das colunas
          Scanner l1 = new Scanner(csv);
          String cabecalho = l1.nextLine();
          String [] separador = cabecalho.split(",");
            //separador:indice das colunas dp csv
          int tamanhoLinha = separador.length;
          //dados.add(separador);
          while(l1.hasNext()){
           String[] vetor = new String[tamanhoLinha] ;
           String linhaDados=l1.nextLine();
           vetor = linhaDados.split(",");
           dados.add(vetor);
         }

         this.titulo=separador;
         this.numColunas=titulo.length;
         this.numLinhas=this.dados.size();		

       }catch(Exception e){
         System.out.println("erro ao abrir o arquivo csv.");   
       }    
       
     } 


     public String getElemento(int linha, int coluna) {
      return this.dados.get(linha)[coluna];
        //[coluna] referencia da coluna do csv lido pelo vetor
        //linha faz referencia ao indice do array que guarda os dados no caso as colunas 
    }
    public void teste (){

      String  join = String.join(",",dados.get(1)[1]);
      System.out.println(join);
      System.out.println("-------------------------");
          //dados.get(i);
          //System.out.println(this.titulo.length);


    }


}