package it.altran.springmvc.myApp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_pubblicazione")
public class TipoPubblicazione {

	
 
	@Id
	@Column(name="ID_TIPO_PUBBLICAZIONE")
	private int idTipoPubblicazione;
	
	@Column(name="DESC_TIPO_PUBBLICAZIONE")
	private String descrizione;

	public int getIdTipoPubblicazione() {
		return idTipoPubblicazione;
	}

	public void setIdTipoPubblicazione(int idTipoPubblicazione) {
		this.idTipoPubblicazione = idTipoPubblicazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
