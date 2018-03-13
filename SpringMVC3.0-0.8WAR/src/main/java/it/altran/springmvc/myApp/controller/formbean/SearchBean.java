package it.altran.springmvc.myApp.controller.formbean;

import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

public class SearchBean {

	@NotEmpty
	private String titolo;
	private String autore;
	
	private String nomeAutore;
	private String cognomeAutore;
	
	private Map<String,String> listaMyAutori;
	
	@NotEmpty
	private String genereSel;
	@NotEmpty
	private String genereSel2;
	private Map<String,String> listaGeneri;
	private Map<String,String> listaGeneri2;
	
	private Map<String,String> listaTipologiaGeneri;
	@NotEmpty
	private String tipologiaSel;
	
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public Map<String, String> getListaGeneri() {
		return listaGeneri;
	}
	public void setListaGeneri(Map<String, String> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}
	public String getGenereSel() {
		return genereSel;
	}
	public void setGenereSel(String genereSel) {
		this.genereSel = genereSel;
	}
	public Map<String, String> getListaGeneri2() {
		return listaGeneri2;
	}
	public void setListaGeneri2(Map<String, String> listaGeneri2) {
		this.listaGeneri2 = listaGeneri2;
	}
	public String getGenereSel2() {
		return genereSel2;
	}
	public void setGenereSel2(String genereSel2) {
		this.genereSel2 = genereSel2;
	}
	public Map<String, String> getListaTipologiaGeneri() {
		return listaTipologiaGeneri;
	}
	public void setListaTipologiaGeneri(Map<String, String> listaTipologiaGeneri) {
		this.listaTipologiaGeneri = listaTipologiaGeneri;
	}
	public String getTipologiaSel() {
		return tipologiaSel;
	}
	public void setTipologiaSel(String tipologiaSel) {
		this.tipologiaSel = tipologiaSel;
	}
	public Map<String, String> getListaMyAutori() {
		return listaMyAutori;
	}
	public void setListaMyAutori(Map<String, String> listaMyAutori) {
		this.listaMyAutori = listaMyAutori;
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

	
	
	
	
}
