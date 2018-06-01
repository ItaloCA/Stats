import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter; 

class Teste{
	
	static void explorador() {
		// JOptionPane.showMessageDialog(null, "Minha mensagem!");
		// JFileChooser fileChooser = new JFileChooser();
		// int retorno = fileChooser.showOpenDialog(null);

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Arquivos csv", "csv");
		chooser.setFileFilter(filter);
		int retorno = chooser.showOpenDialog(null);

		// if (retorno == JFileChooser.APPROVE_OPTION) {
		// 	File file = fileChooser.getSelectedFile();
  // // faz alguma coisa com arquivo
		// } else {
  // // dialogo cancelado
		// }
	}
}