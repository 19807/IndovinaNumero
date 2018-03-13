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

	private int NMAX = 100;
	private int TMAX = 7;

	private int segreto; // numero da indovinare
	private int tentativi; // tentativi già fatti

	private boolean inGame = false; // dice se c'è una partita in corso o no
	// parto con una partita non iniziata

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

		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.inGame = true;
		this.tentativi = 0;

		btnNuova.setDisable(true);
		boxGioco.setDisable(false); // false per abilitare

		txtCurr.setText(String.format("%d", this.tentativi));
		txtMax.setText(String.format("%d", this.TMAX));
		txtLog.clear();
		txtTentativo.clear();
		
		txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));
	}

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
			if(num==this.segreto) {
				txtLog.appendText("Hai vinto!\n");
				boxGioco.setDisable(true);
				btnNuova.setDisable(false);
				this.inGame = false;

			}

			else {
				//tentativo errato
				this.tentativi++;
				txtCurr.setText(String.format("%d", this.tentativi));
				if(this.tentativi==TMAX) {
					//perso

					txtLog.appendText(String.format("Hai perso!\n Il numero era: %d", this.segreto));
					boxGioco.setDisable(true);
					btnNuova.setDisable(false);
					this.inGame = false;
				}
				else {
					if(num<segreto) {
						//troppo basso
						txtLog.appendText("Troppo basso!\n");
					}
					else {

						//troppo alto
						txtLog.appendText("Troppo alto!\n");

					}
				}
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
}
