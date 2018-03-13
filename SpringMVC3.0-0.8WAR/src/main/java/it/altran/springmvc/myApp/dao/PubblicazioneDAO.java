package it.altran.springmvc.myApp.dao;


import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.bean.Pubblicazione;
import it.altran.springmvc.myApp.bean.PubblicazioneAutore;
import it.altran.springmvc.myApp.bean.PubblicazioneAutorePK;
import it.altran.springmvc.myApp.bean.PubblicazioneGenere;
import it.altran.springmvc.myApp.bean.PubblicazioneGenerePK;
import it.altran.springmvc.myApp.bean.TipoPubblicazione;
import it.altran.springmvc.myApp.controller.formbean.InsertBean;
import it.altran.springmvc.myApp.controller.formbean.InsertBean2;
import it.altran.springmvc.myApp.persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map; 

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
//import javax.transaction.Transactional;



//import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class PubblicazioneDAO {
	
	@Autowired
	private PropertiesTable propertiesTable;
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public  List<Pubblicazione> getPubblicazione(String titoloSel, String autoreSel){
		
//		 PersistenceManager manager = PersistenceManager.getInstance();
//		 EntityManager em = manager.getEntityManager();
		 
	
		 //TODO:Only for test
		 System.out.println(">>>>>>titoloSel:"+titoloSel);
		 System.out.println(">>>>>>autoreSel:"+autoreSel);
		 
		 String myQuery ="SELECT DISTINCT a  FROM Pubblicazione a";
		 
		 String mQueryJOIN = " JOIN FETCH a.listaAutori b JOIN FETCH b.autore c ";
		 
		 //Se ho la condizione sulla tabella di join
		 //Aggiungo la tabella di JOIN altrimenti no
//		 if ( (autoreSel!= null)&&!(autoreSel.trim().equals(""))  )
			 myQuery+=mQueryJOIN;
		 
		 	myQuery+=" WHERE  1= 1 ";
		 
		 if ( (titoloSel!= null)&&!(titoloSel.trim().equals(""))  )
			 myQuery +=" AND a.titolo LIKE :titolo"; 
	
		 if ( (autoreSel!= null)&&!(autoreSel.trim().equals(""))  )
			 myQuery +=" AND c.cognome  LIKE :autore"; 
	
		 
		 System.out.println(">>>>>>>MyQuery:"+myQuery);
		 
		 Query query = entityManager.createQuery(myQuery) ;
		 
		
		 
		 
		 if ( (titoloSel!= null)&&!(titoloSel.trim().equals(""))  )
			 query.setParameter("titolo", "%"+titoloSel+"%");
		 
		 if ( (autoreSel!= null)&&!(autoreSel.trim().equals(""))  )
			 query.setParameter("autore", "%"+autoreSel+"%");
		 
		 List<Pubblicazione> listaPubblicazioni = query.getResultList();
		

		
		 
//		 for(int i=0; i < listaPubblicazioni.size(); i++){
//				
//			Pubblicazione pubblicazione = listaPubblicazioni.get(i);
//		 Collection<PubblicazioneAutore> listaA = pubblicazione.getListaAutori();
//	
//			Iterator<PubblicazioneAutore> iterator = listaA.iterator();
//			
//			String stringaAutori="";
//			int index = 0;
//			while(iterator.hasNext()){
//				PubblicazioneAutore pubblicazioneAutore = iterator.next();
//				Autore autore = pubblicazioneAutore.getAutore();
//				stringaAutori= autore.getNome()+" "+autore.getCognome();
//			}
//		 }
		 
		 return listaPubblicazioni;
	}
	
	
	public void insertPubblicazione(InsertBean bean){
		
		 PersistenceManager manager = PersistenceManager.getInstance();
		 EntityManager em = manager.getEntityManager();
	//	 Genere g =  em.find(Genere.class, 1);
		 
		 Pubblicazione pubblicazione = new Pubblicazione();
//		 TipoPubblicazione tipoPubblicazione = new TipoPubblicazione();
		
		 PubblicazioneGenere pubblicazioneGenere = new PubblicazioneGenere();
		 
		 pubblicazione.setTitolo(bean.getTitolo());
		 
		 Map<String, String> map= propertiesTable.getListaTipologiaPubblicazione();
		 int id = Integer.parseInt(bean.getTipologiaSelezionato());
		 String descrizione = map.get(bean.getTipologiaSelezionato());
		 TipoPubblicazione tipoPubblicazione = new TipoPubblicazione();
		 tipoPubblicazione.setIdTipoPubblicazione(id);
		 tipoPubblicazione.setDescrizione(descrizione);
		 
		 
		 pubblicazione.setIdTipoPubblicazione(tipoPubblicazione);
		 
//		 PubblicazioneAutorePK pubblicazioneAutorePK = new PubblicazioneAutorePK();
//		 pubblicazioneAutorePK.setIdAutore(Integer.parseInt(bean.getAutore()));
//		 pubblicazioneAutorePK.setIdA(Integer.parseInt(bean.getAutore()));
		 
//		 pubblicazioneAutore.setIdP(Integer.parseInt(bean.getAutore()));
		 em.getTransaction().begin();
		 em.persist(pubblicazione);
		 
		 int idPubblicazione = pubblicazione.getIdPubblicazione();
		 System.out.println(">>>>>><indice restiruito:"+idPubblicazione);
		 
		 //
		 
		 ArrayList<String> autori = bean.getAutori();
		 for(int i =0; i< autori.size();i++){
			 String idAutore = autori.get(i);
			 
			 PubblicazioneAutorePK pubblicazioneAutorePK = new PubblicazioneAutorePK();
			 pubblicazioneAutorePK.setIdAutore(Integer.parseInt(idAutore));
			 pubblicazioneAutorePK.setIdPubblicazione(idPubblicazione);
			 pubblicazioneAutorePK.setIdProfessione(1);
			 
			 PubblicazioneAutore pubblicazioneAutore = new PubblicazioneAutore();
			 pubblicazioneAutore.setPubblicazioneAutorePK(pubblicazioneAutorePK);
			 
			 em.persist(pubblicazioneAutore);
		 }
		 
		 
		 PubblicazioneGenerePK pubblicazioneGenerePK = new PubblicazioneGenerePK();
		 pubblicazioneGenerePK.setIdPubblicazione(idPubblicazione);
		 int idgenere = Integer.parseInt(bean.getGenereSelezionato());
		 pubblicazioneGenerePK.setIdgenere(idgenere);
		 pubblicazioneGenere.setPubblicazioneGenerePK(pubblicazioneGenerePK);
		 
		 em.persist(pubblicazioneGenere);
		 
		 
		 em.getTransaction().commit();
		 
//		 idPubblicazione = pubblicazione.getIdPubblicazione();
//		 System.out.println(">>>>>><indice restiruito:"+idPubblicazione);
		 em.close();
		 
		 
//		 Query query = em.createQuery("SELECT a FROM Autore a WHERE a.nome LIKE :nome AND a.cognome LIKE :cognome");
//		 query.setParameter("nome", "%"+nomesel+"%");
//		 query.setParameter("cognome", "%"+cognomeSel+"%");
//		 
//		 List<Autore> listaAutori = query.getResultList();
		 return;
	}

	
	@Transactional(propagation=Propagation.REQUIRED)
	public void insertPubblicazione2(InsertBean2 bean,int idAutore){
		

		 
		 Pubblicazione pubblicazione = new Pubblicazione();

		
		 PubblicazioneGenere pubblicazioneGenere = new PubblicazioneGenere();
		 
		 pubblicazione.setTitolo(bean.getTitolo());
		 
		 Map<String, String> map= propertiesTable.getListaTipologiaPubblicazione();
		 int id = Integer.parseInt(bean.getTipologiaSelezionato());
		 String descrizione = map.get(bean.getTipologiaSelezionato());
		 TipoPubblicazione tipoPubblicazione = new TipoPubblicazione();
		 tipoPubblicazione.setIdTipoPubblicazione(id);
		 tipoPubblicazione.setDescrizione(descrizione);
		 
		 
		 pubblicazione.setIdTipoPubblicazione(tipoPubblicazione);
		 
		 System.out.println(">>>>>>SALVO PUBBLICAZIONE");
		 entityManager.persist(pubblicazione);
		 
		 int idPubblicazione = pubblicazione.getIdPubblicazione();
		 
		 System.out.println(">>>>>><indice restiruito:"+idPubblicazione);
		 

			 
			 PubblicazioneAutorePK pubblicazioneAutorePK = new PubblicazioneAutorePK();
			 pubblicazioneAutorePK.setIdAutore(idAutore);
			 pubblicazioneAutorePK.setIdPubblicazione(idPubblicazione);
			 pubblicazioneAutorePK.setIdProfessione(1);
			 
			 PubblicazioneAutore pubblicazioneAutore = new PubblicazioneAutore();
			 pubblicazioneAutore.setPubblicazioneAutorePK(pubblicazioneAutorePK);
			 
			 System.out.println(">>>>>>SALVO PUBBLICAZIONE_AUTORE");
			 entityManager.persist(pubblicazioneAutore);

		 
		 
		 PubblicazioneGenerePK pubblicazioneGenerePK = new PubblicazioneGenerePK();
		 pubblicazioneGenerePK.setIdPubblicazione(idPubblicazione);
		 int idgenere = Integer.parseInt(bean.getGenereSelezionato());
		 pubblicazioneGenerePK.setIdgenere(idgenere);
		 pubblicazioneGenere.setPubblicazioneGenerePK(pubblicazioneGenerePK);
		 
		 System.out.println(">>>>>>SALVO PUBBLICAZIONE_GENERE");
		 entityManager.persist(pubblicazioneGenere);
		 
		
		 return;
	}
}
