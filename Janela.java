import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;

public class Janela extends Application{
	public File arq;

	public void abrir(String[] args) {
		launch();
	}

	public void start(Stage janela) throws Exception{
		
		LerArquivo leitor = new LerArquivo();
		StackPane layout = new StackPane();
		

		Button confirmar = new Button("Selecione um arquivo csv");
		confirmar.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event){
				Importador i = new Importador();
				arq = i.explorador(); 
			
				if (arq != null){
					leitor.run(arq);
				} else {
					System.out.println("Arquivo nulo\n");
				}
			}
		});	


		layout.getChildren().add(confirmar);
		Scene cena = new Scene(layout,300,300);
		

		janela.setTitle("Stats");
		janela.setScene(cena);
		janela.show();
		
		



	}
}