package it.altran.springmvc.myApp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "genere")
public class Genere {
	
	@Id
	@Column(name="ID_GENERE")
	private int idGenere;
	
	@Column(name="DESC_GENERE")
	private String descrizione;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CATEGORIA_GENERE_ID_CATEGORIA_GENERE", referencedColumnName ="ID_CATEGORIA_GENERE",unique=true,insertable=false,updatable=false)
	})
	private CategoriaGenere categoriaGenere;


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getIdGenere() {
		return idGenere;
	}

	public void setIdGenere(int idGenere) {
		this.idGenere = idGenere;
	}

	public CategoriaGenere getCategoriaGenere() {
		return categoriaGenere;
	}

	public void setCategoriaGenere(CategoriaGenere categoriaGenere) {
		this.categoriaGenere = categoriaGenere;
	}

	
	
}
