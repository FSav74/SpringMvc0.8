package it.altran.springmvc.myApp.controller.formbean;

import java.util.Map;

public class InsertClientFormBean {
	
	private String username;

	private String password;
	
	private String enabled;
	
	private String roleSelezionato;
	
	private String name;
	
	private String surname;
	
	private String mail;
	
	private Map<String,String> listaRuoli;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getRoleSelezionato() {
		return roleSelezionato;
	}

	public void setRoleSelezionato(String roleSelezionato) {
		this.roleSelezionato = roleSelezionato;
	}

	public Map<String, String> getListaRuoli() {
		return listaRuoli;
	}

	public void setListaRuoli(Map<String, String> listaRuoli) {
		this.listaRuoli = listaRuoli;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
}
