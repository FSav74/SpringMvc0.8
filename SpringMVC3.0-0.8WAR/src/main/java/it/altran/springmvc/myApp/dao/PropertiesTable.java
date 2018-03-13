package it.altran.springmvc.myApp.dao;


import it.altran.springmvc.myApp.bean.CategoriaGenere;
import it.altran.springmvc.myApp.bean.Genere;
import it.altran.springmvc.myApp.bean.TipoPubblicazione;
import it.altran.springmvc.myApp.persistence.PersistenceManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class PropertiesTable {
	
	private Map<String, String> listaCategorieView;
	private Map<String, String> listaTipologiaPubblicazioneView;
	
	private List<CategoriaGenere> listaCategorie = null;

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@PostConstruct
	@Transactional
	public void init(){
		
		System.out.println("Costruttore PropertiesTable...carico le liste");
		
		
//		 PersistenceManager manager = PersistenceManager.getInstance();
//		 EntityManager em = manager.getEntityManager();

		 
		 Query query = entityManager.createQuery("SELECT c FROM CategoriaGenere c JOIN FETCH c.listaGeneri d");
		 
		 listaCategorie = query.getResultList();
		 
		 listaCategorieView = new HashMap<String,String>();
		 listaCategorieView.put("0"," ");
		 
		 for (int i=0; i< listaCategorie.size();i++){
			 CategoriaGenere g = listaCategorie.get(i);
			 listaCategorieView.put(""+g.getIdCategoriaGenere(), g.getDescrizioneCategoria());
		 }
		 
		 
		 //Tipologia pubblicazione
		 listaTipologiaPubblicazioneView = new HashMap<String,String>();
		 Query queryTipo = entityManager.createQuery("SELECT c FROM TipoPubblicazione c");
		 List<TipoPubblicazione> listaTipologiaPubblicazioni = queryTipo.getResultList();
		 for (int i=0; i< listaTipologiaPubblicazioni.size();i++){
			 TipoPubblicazione g = listaTipologiaPubblicazioni.get(i);
			 listaTipologiaPubblicazioneView.put(""+g.getIdTipoPubblicazione(), g.getDescrizione());
		 }
		 
		
	}
	
	public  Map<String, String> getListaGeneri(){
		return listaCategorieView;
	}
	
	public  Map<String, String> getListaTipologiaPubblicazione(){
		return listaTipologiaPubblicazioneView;
	}
	
	
	public  Map<String, String> getGenere(String IdCategoriaGenere){
		
		 int IdCategoriaGenereInt = Integer.parseInt(IdCategoriaGenere);
		 Collection<Genere> listaGeneri = null;
		
		 
		 for (int i=0; i< listaCategorie.size();i++){
			 CategoriaGenere gOriginale = listaCategorie.get(i);
			 
			 
			 
			 if (gOriginale.getIdCategoriaGenere()==IdCategoriaGenereInt){
				 
				 listaGeneri = gOriginale.getListaGeneri();
				 break;
			 }
		 }
		 
		 HashMap<String,String> listageneriView = null;
		 
		 if (listaGeneri!=null){

			 listageneriView = new HashMap<String,String>();
			 Iterator<Genere> iterator = listaGeneri.iterator();
			 while(iterator.hasNext()){
				 Genere g = iterator.next();

				  listageneriView.put(""+g.getIdGenere(), g.getDescrizione());
				
				
			 }
			
			 
			 
		 }
		 
		 return listageneriView;
		
	}

}
