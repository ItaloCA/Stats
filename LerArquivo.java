import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LerArquivo {
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

      public LerArquivo() {}

      public LerArquivo(String caminho){
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
          dados.add(separador);
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
      return this.dados.get(linha+1)[coluna];
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

    

    public int getNumLinhas(){
      return this.numLinhas;
    }
    public int getNumColunas(){
      return this.numColunas;
    }
/*
  public boolean colNumerica(int coluna){
      int linha = 0;
      try{
        String elemento =  this.getElemento(linha,coluna);
        while(elemento.equals("")){
          linha++;
        elemento=this.getElemento(linha,coluna);//percorre a coluna,descendo linha a linha até achar algo não vazio
        }
      }catch(Exception e){
      System.out.println("CSV com problema de leitura.");
      }
      //quando o elemento é achado este é comparado e ocorre verificação se é ou não numerico
      if(elemento.matches("^([+-]?\\d*\\.?\\d*)$")){
        return true;
      }else{
        return false;
      } 
  }
//*/
  public double converteNumerico(int linha,int coluna){
    return Double.parseDouble(this.getElemento(linha, coluna));
  }
/*
  public String [] colsNumericas(){
    int numeroColunas = this.getNumColunas();
    String [] Numericas = new String [numeroColunas];
    int cont;
    for(int i=0;i<numeroColunas;i++){
      if(this.colNumerica(i)){
        Numericas[cont]=this.titulo[i];//(titulo) atributo que guarda a primeira linha(0) do csv ,assim passa para o vetor o nome da coluna 
        cont++;
      }
    }
    return Numericas;
    
  } 
//*/

}