package it.altran.springmvc.myApp.bean;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "pubblicazione")
public class Pubblicazione {
	
	@Id
	@Column(name="ID_PUBBLICAZIONE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPubblicazione;
	
	@Column(name="TITOLO")
	private String titolo;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_TIPO_PUBBLICAZIONE")
	private TipoPubblicazione idTipoPubblicazione;
	
	@OneToMany(mappedBy="pubblicazione",targetEntity=PubblicazioneGenere.class,fetch=FetchType.LAZY)
	private Collection<PubblicazioneGenere> listaGeneri;
	
	@OneToMany(mappedBy="pubblicazione",targetEntity=PubblicazioneAutore.class,fetch=FetchType.LAZY)
	private Collection<PubblicazioneAutore> listaAutori;

	public int getIdPubblicazione() {
		return idPubblicazione;
	}

	public void setIdPubblicazione(int idPubblicazione) {
		this.idPubblicazione = idPubblicazione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public TipoPubblicazione getIdTipoPubblicazione() {
		return idTipoPubblicazione;
	}

	public void setIdTipoPubblicazione(TipoPubblicazione idTipoPubblicazione) {
		this.idTipoPubblicazione = idTipoPubblicazione;
	}

	public Collection<PubblicazioneGenere> getListaGeneri() {
		return listaGeneri;
	}

	public void setListaGeneri(Collection<PubblicazioneGenere> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}

	public Collection<PubblicazioneAutore> getListaAutori() {
		return listaAutori;
	}

	public void setListaAutori(Collection<PubblicazioneAutore> listaAutori) {
		this.listaAutori = listaAutori;
	}
	
	
}
