package it.polito.tdp.indonumero;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//!ATTENZIONE modifico queste due righe:
			//faccio questo per dire qual è la classe controller
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumero.fxml"));
			BorderPane root = (BorderPane)loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Model model = new Model(); //creo istanza del modello
			((IndoNumeroController)loader.getController()).setModel(model); //modello creato da main e passato in gestione al controller
			
			//modello e controller sono in comunicazione
			//il controller sa qual è il modello, il modello non sa qual è il controller (e non deve saperlo)
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
