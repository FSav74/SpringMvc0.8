package it.altran.springmvc.myApp.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente {

	
	@Id
	@Column(name="ID_CLIENTE")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCliente;
	
	@Column(name="NOME_CLIENTE")
	private String nome;
	
	@Column(name="COGNOME_CLIENTE")
	private String cognome;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="DATA_INSERIMENTO")
	private Date dataInserimento;
	
	@Column(name="DATA_AGGIORNAMENTO")
	private Date dataAggiornamneto;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	public Date getDataAggiornamneto() {
		return dataAggiornamneto;
	}

	public void setDataAggiornamneto(Date dataAggiornamneto) {
		this.dataAggiornamneto = dataAggiornamneto;
	}


	
	
	
}
