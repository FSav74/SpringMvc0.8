package it.altran.springmvc.myApp.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class PubblicazioneGenerePK implements Serializable{

	@Column(name="PUBBLICAZIONE_ID_PUBBLICAZIONE")
	private int idPubblicazione;

	@Column(name="GENERE_ID_GENERE")
	private int idgenere;

	public int getIdPubblicazione() {
		return idPubblicazione;
	}

	public void setIdPubblicazione(int idPubblicazione) {
		this.idPubblicazione = idPubblicazione;
	}

	public int getIdgenere() {
		return idgenere;
	}

	public void setIdgenere(int idgenere) {
		this.idgenere = idgenere;
	}
	
}
