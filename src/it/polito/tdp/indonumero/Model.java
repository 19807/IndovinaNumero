package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

//non importo nulla da JavaFX!!!

public class Model {

	private int NMAX = 100;
	private int TMAX = 7;

	private int segreto; // numero da indovinare
	private int tentativi; // tentativi già fatti

	private boolean inGame = false; // dice se c'è una partita in corso o no


	public Model() {
		this.inGame = false;
	}


	/**
	 * Avia una nuova partita generando un nuovo numero segreto
	 */
	public void newGame() {

		this.segreto = (int) (Math.random() * NMAX) + 1;

		this.tentativi = 0;
		this.inGame = true;
	}

	/**
	 * fai un  nuovo tentativo di indovinare il numero segreto
	 * @param = t valore numerico del tentativo
	 * @return = 0 se hai indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {

		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}

		//if(t<1 || t>this.NMAX) invece scrivo

		if(!valoreValido(t)){
			throw new InvalidParameterException("Tentativo fuori range");
		}

		this.tentativi++ ;
		if(this.tentativi==this.TMAX)
			this.inGame = false;      //partita finita

		if(t==this.segreto) {
			this.inGame = false;      //partita finita
			return 0;
		}

		if(t<segreto)
			return -1;

		return +1;

	}


	/**controlla se il tentativo fornito rispetta le regole formali del gioco
	 * cioè è nel range da 1 a NMAX
	 * @param tentativo
	 * @return true se tentativo è valido
	 */
	public boolean valoreValido (int tentativo) {
		return tentativo >-1 && tentativo <= this.NMAX;
	}

	/**
	 * rendo visibile al controller la situazione della variabile InGame con un get
	 */
	public boolean isInGame() {
		return inGame;
	}

	public int getTentativi() {
		return this.tentativi;
	}


	public int getNMAX() {
		return NMAX;
	}


	public int getTMAX() {
		return TMAX;
	}


	public int getSegreto() {
		return segreto;
	}


}
