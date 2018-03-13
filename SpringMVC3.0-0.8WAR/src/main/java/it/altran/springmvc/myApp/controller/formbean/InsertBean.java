package it.altran.springmvc.myApp.controller.formbean;

import java.util.ArrayList;
import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

public class InsertBean {

	
	private String titolo;
	private ArrayList<String> autori; 
	
	
	private String tipologiaSelezionato;
	private String genereSelezionato;

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public ArrayList<String> getAutori() {
		return autori;
	}
	public void setAutori(ArrayList<String> autori) {
		this.autori = autori;
	}
	public String getTipologiaSelezionato() {
		return tipologiaSelezionato;
	}
	public void setTipologiaSelezionato(String tipologiaSelezionato) {
		this.tipologiaSelezionato = tipologiaSelezionato;
	}
	public String getGenereSelezionato() {
		return genereSelezionato;
	}
	public void setGenereSelezionato(String genereSelezionato) {
		this.genereSelezionato = genereSelezionato;
	}
	

	
	
	
}
