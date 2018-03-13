package it.altran.springmvc.myApp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PubblicazioneAutorePK  implements Serializable{

	
	@Column(name="ID_PUBBLICAZIONE")
	private int idPubblicazione;

	@Column(name="ID_AUTORE")
	private int idAutore;
	
	@Column(name="ID_PROFESSIONE")
	private int idProfessione;

	public int getIdPubblicazione() {
		return idPubblicazione;
	}

	public void setIdPubblicazione(int idPubblicazione) {
		this.idPubblicazione = idPubblicazione;
	}

	public int getIdAutore() {
		return idAutore;
	}

	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}

	public int getIdProfessione() {
		return idProfessione;
	}

	public void setIdProfessione(int idProfessione) {
		this.idProfessione = idProfessione;
	}
	
	
	
}
