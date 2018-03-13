package it.altran.springmvc.myApp.bean;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "categoria_genere")
public class CategoriaGenere implements Cloneable{
	
	@Id
	@Column(name="ID_CATEGORIA_GENERE")
	private int idCategoriaGenere;
	
	@Column(name="DESC_CATEGORIA_GENERE")
	private String descrizioneCategoria;

	public int getIdCategoriaGenere() {
		return idCategoriaGenere;
	}

	public void setIdCategoriaGenere(int idCategoriaGenere) {
		this.idCategoriaGenere = idCategoriaGenere;
	}

	public String getDescrizioneCategoria() {
		return descrizioneCategoria;
	}

	public void setDescrizioneCategoria(String descrizioneCategoria) {
		this.descrizioneCategoria = descrizioneCategoria;
	}
	
	@OneToMany(mappedBy="categoriaGenere",targetEntity=Genere.class,fetch=FetchType.LAZY)
	private Collection<Genere> listaGeneri;

	public Collection<Genere> getListaGeneri() {
		return listaGeneri;
	}

	public void setListaGeneri(Collection<Genere> listaGeneri) {
		this.listaGeneri = listaGeneri;
	}
	
	@Override
	public CategoriaGenere clone() throws CloneNotSupportedException{
//		CategoriaGenere cat = new CategoriaGenere();
		
		CategoriaGenere cat =  (CategoriaGenere) super.clone();
		cat.setIdCategoriaGenere(this.getIdCategoriaGenere());
		cat.setDescrizioneCategoria(this.getDescrizioneCategoria());
		cat.getListaGeneri().clear();
		return cat;
	}

}
