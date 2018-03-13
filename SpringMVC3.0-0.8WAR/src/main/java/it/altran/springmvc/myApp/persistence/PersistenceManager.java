package it.altran.springmvc.myApp.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/*
 * Ho creato un singleton per la gestione del PersistenceEntity.
 * 
 * 
 * 
 * 
 * 
 */
public class PersistenceManager {
	  
	  private static PersistenceManager instance = null;
	  private static EntityManagerFactory emFactory;
	  private static Object checkObject = new Object();;
	  
	  
	 
	  private PersistenceManager() {
	    // "jpa-example" was the value of the name attribute of the
	    // persistence-unit element.
	    emFactory = Persistence.createEntityManagerFactory("jpa-example");
	  }
	  
	  
	  public static PersistenceManager getInstance(){
		  if(instance==null){
			  synchronized (checkObject) {
				  if(instance==null) instance= new PersistenceManager();
			}
		  }
		  return instance;
	  }
	 
	  public EntityManager getEntityManager() {
	    return emFactory.createEntityManager();
	  }
	 
	  public void close() {
	    emFactory.close();
	  }
	}