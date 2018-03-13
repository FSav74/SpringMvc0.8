package it.altran.springmvc.myApp.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PUBBLICAZIONE_GENERE")
public class PubblicazioneGenere {
	
	@EmbeddedId
	private PubblicazioneGenerePK pubblicazioneGenerePK;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PUBBLICAZIONE_ID_PUBBLICAZIONE", referencedColumnName= "ID_PUBBLICAZIONE",unique=true,insertable=false,updatable=false)
	})
	private Pubblicazione pubblicazione;
	
	public PubblicazioneGenerePK getPubblicazioneGenerePK() {
		return pubblicazioneGenerePK;
	}

	public void setPubblicazioneGenerePK(PubblicazioneGenerePK pubblicazioneGenerePK) {
		this.pubblicazioneGenerePK = pubblicazioneGenerePK;
	}

	public Pubblicazione getPubblicazione() {
		return pubblicazione;
	}

	public void setPubblicazione(Pubblicazione pubblicazione) {
		this.pubblicazione = pubblicazione;
	}

	
	
	
	
}
