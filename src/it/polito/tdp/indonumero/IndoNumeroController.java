/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private Model model;  //non c'è new, non sto creando il modello!!
	

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnNuova"
	private Button btnNuova; // Value injected by FXMLLoader

	@FXML // fx:id="txtCurr"
	private TextField txtCurr; // Value injected by FXMLLoader

	@FXML // fx:id="txtMax"
	private TextField txtMax; // Value injected by FXMLLoader

	@FXML // fx:id="boxGioco"
	private HBox boxGioco; // Value injected by FXMLLoader

	@FXML // fx:id="txtTentativo"
	private TextField txtTentativo; // Value injected by FXMLLoader

	@FXML // fx:id="txtLog"
	private TextArea txtLog; // Value injected by FXMLLoader

	@FXML
	void handleNuova(ActionEvent event) {
		// il metodo viene attivato quando il giocatore preme sul bottone "nuova
		// partita"

		model.newGame();

		btnNuova.setDisable(true);
		boxGioco.setDisable(false); // false per abilitare

		txtCurr.setText(String.format("%d", model.getTentativi()));
		txtMax.setText(String.format("%d", model.getTMAX()));
		txtLog.clear();
		txtTentativo.clear();
		
		txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));
		
		//nel controller non creo copie di quello che c'è gia nel modello
	}

	
	/**
	 * il controller deve acquisire i dati, validarli, ottenere i risultati dal modello e visualizzarli all'utente
	 */
	
	@FXML
	void handleProva(ActionEvent event) {
		String numS = txtTentativo.getText(); 

		if(numS.length()==0) {
			txtLog.appendText("Devi inserire un numero!\n");
			return ;
		}

		try {
			int num = Integer.parseInt(numS);
			//numero intero
			
			//invece di: if(num<1 || num>model.getNMAX())  scrivo:
			
			if(!model.valoreValido(num)){
				txtLog.appendText("Valore fuori dall'intervallo consentito\n");
				return; 
			}
			
			//provo a fare un tentativo
			int risultato = model.tentativo(num);
			txtCurr.setText(String.format(("%d"), model.getTentativi()));
			
			if(risultato==0){
				txtLog.appendText("Hai vinto!\n");
			}
			
			else if(risultato<0) {
				txtLog.appendText("Troppo basso!\n");
			}
			else {
				txtLog.appendText("Troppo alto!\n");
			}
			
			//nel controller non voglio fare il controllo se la partita è finita o no, lo farò nel model
			//il controller non deve sapere le regole del gioco, sa solo che sto inserendo dei valori
			
			if(!model.isInGame()) {
				//la partita è finita (vittoria o sconfitta)
				if(risultato!=0) {   //partita finita con sconfitta
					txtLog.appendText("Hai perso!\n");
				txtLog.appendText(String.format("Il numero segreto era: %d\n", model.getSegreto()));
				}
			
			
			//"chiudi" la partita
			btnNuova.setDisable(false);
			boxGioco.setDisable(true);
			
			}
			
		}catch(NumberFormatException ex) {
			txtLog.appendText("Il dato inserito non e' numerico\n");
			return ;

		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
		assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

	}
	
	public void setModel(Model model) {  //creo metodo set del model
		this.model = model;
	}
}
