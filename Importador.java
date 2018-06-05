//package stats2;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.io.File;

class Importador{
	//inicia o metodo para escolher o arquivo
	static File explorador() {
		//JFileChooser eh o objeto que abre o explorador de arquivos
		JFileChooser chooser = new JFileChooser();
		//filtra a extensao do arquivo para somente csv
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Arquivos csv", "csv");
		chooser.setFileFilter(filter);
		int retorno = chooser.showOpenDialog(null);

		 if (retorno == JFileChooser.APPROVE_OPTION) {
		 	File file = chooser.getSelectedFile();
		 	// retorna o arquivo escolhido
		 	return file;
		 }//retorna nulo caso o usuario n tenha escolhido um arquivo
		return null;
	}

}