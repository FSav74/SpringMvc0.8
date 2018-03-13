package it.altran.springmvc.myApp.dao;

import java.util.List;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.controller.formbean.InsertBean;
import it.altran.springmvc.myApp.controller.formbean.InsertBean2;
import it.altran.springmvc.myApp.persistence.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;





import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class AuthorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public  List<Autore> getAutore(String nomesel, String cognomeSel){
	
//		 PersistenceManager manager = PersistenceManager.getInstance();
//		 EntityManager em = manager.getEntityManager();
	//	 Genere g =  em.find(Genere.class, 1);
		 
		 Query query = entityManager.createQuery("SELECT a FROM Autore a WHERE a.nome LIKE :nome AND a.cognome LIKE :cognome");
		 query.setParameter("nome", "%"+nomesel+"%");
		 query.setParameter("cognome", "%"+cognomeSel+"%");
		 
		 List<Autore> listaAutori = query.getResultList();
		 return listaAutori;
	}
	
	@Transactional
	public  Autore getAutoreFromID(String id){
		
//		 PersistenceManager manager = PersistenceManager.getInstance();
//		 EntityManager em = manager.getEntityManager();
	//	 Genere g =  em.find(Genere.class, 1);
		 
		 Query query = entityManager.createQuery("SELECT a FROM Autore a WHERE a.id = :id ");
		 query.setParameter("id", new Integer(id));
		
		 Autore autore = (Autore) query.getSingleResult();
		 return autore;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int insertAutore(InsertBean2 bean){
		Autore autore = new Autore();
		autore.setNome(bean.getNomeAutore());
		autore.setCognome(bean.getCognomeAutore());
		
		entityManager.persist(autore);
		
		return autore.getIdAutore();
	}
	
	
}
