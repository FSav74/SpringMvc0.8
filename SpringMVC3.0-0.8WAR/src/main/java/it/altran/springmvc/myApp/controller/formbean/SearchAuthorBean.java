package it.altran.springmvc.myApp.controller.formbean;

public class SearchAuthorBean {

	private String codice;
	private String nomeAutore;
	private String cognomeAutore;
	
	private String numeroRisultati;
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getNomeAutore() {
		return nomeAutore;
	}
	public void setNomeAutore(String nomeAutore) {
		this.nomeAutore = nomeAutore;
	}
	public String getCognomeAutore() {
		return cognomeAutore;
	}
	public void setCognomeAutore(String cognomeAutore) {
		this.cognomeAutore = cognomeAutore;
	}
	public String getNumeroRisultati() {
		return numeroRisultati;
	}
	public void setNumeroRisultati(String numeroRisultati) {
		this.numeroRisultati = numeroRisultati;
	}
	
	
	
}
