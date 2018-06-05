//package stats2;

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
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.geometry.Insets;



public class Main extends Application{
	//elementos da interface grafica
	Button confirmar;
	Scene cena;
	File arquivo;
	CalcMetricas csv;
	
	
	
	public static void main(String[] args) {
		launch();
	}public void start(Stage janela) throws Exception{
		//setup da tela
		BorderPane layout = new BorderPane();
		StackPane hbox = new StackPane();
		layout.setBottom(hbox);
		layout.setId("pane");
		confirmar = new Button("Selecione um arquivo csv");
		hbox.getChildren().add(confirmar);
		hbox.setPadding(new Insets(100, 100, 100, 100));
		cena = new Scene(layout,950,700);
		cena.getStylesheets().addAll(this.getClass().getResource("Style.css").toExternalForm());
		janela.setTitle("Stats");
		janela.setScene(cena);
		janela.show();
		
		
		//tratamento de acoes
		confirmar.setOnAction(e ->{
			//procura um arquivo
			arquivo = Importador.explorador();
			if(arquivo != null) {
				//le o csv
				csv = new CalcMetricas(arquivo);
				janela.close();
				SecondStage s = new SecondStage(csv);
				//inicia a segunda parte
				s.secondStage();
			}
		});	
	}
}