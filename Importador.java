import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; 
import java.io.File;

class Importador{
	
	static File explorador() {
		// JOptionPane.showMessageDialog(null, "Minha mensagem!");
		// JFileChooser fileChooser = new JFileChooser();
		// int retorno = fileChooser.showOpenDialog(null);

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Arquivos csv", "csv");
		chooser.setFileFilter(filter);
		int retorno = chooser.showOpenDialog(null);

		 if (retorno == JFileChooser.APPROVE_OPTION) {
		 	File file = chooser.getSelectedFile();
		 	return file;
  // // faz alguma coisa com arquivo
		 } else {
		 	System.out.println("Deu ruim\n");
  // // dialogo cancelado
		 }
		return null;
	}

}