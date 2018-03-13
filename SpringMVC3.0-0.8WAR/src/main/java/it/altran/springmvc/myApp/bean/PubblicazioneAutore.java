package it.altran.springmvc.myApp.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PUBBLICAZIONE_AUTORE")
public class PubblicazioneAutore {

	@EmbeddedId
	private PubblicazioneAutorePK pubblicazioneAutorePK;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="ID_PUBBLICAZIONE", referencedColumnName= "ID_PUBBLICAZIONE",unique=true,insertable=false,updatable=false)
	})
	private Pubblicazione pubblicazione;
	
	
	@ManyToOne(fetch= FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name="ID_AUTORE", referencedColumnName= "ID_AUTORE",unique=true,insertable=false,updatable=false)
//	})
	@JoinColumn(name="ID_AUTORE",unique=true,insertable=false,updatable=false)
	private Autore autore;
	
	
	public PubblicazioneAutorePK getPubblicazioneAutorePK() {
		return pubblicazioneAutorePK;
	}
	
	

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}



	public void setPubblicazione(Pubblicazione pubblicazione) {
		this.pubblicazione = pubblicazione;
	}



	public void setPubblicazioneAutorePK(PubblicazioneAutorePK pubblicazioneAutorePK) {
		this.pubblicazioneAutorePK = pubblicazioneAutorePK;
	}



	public Autore getAutore() {
		return autore;
	}



	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	
	
	
}
