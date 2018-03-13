package it.altran.springmvc.myApp.dao.admin;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.bean.Cliente;
import it.altran.springmvc.myApp.bean.security.User;
import it.altran.springmvc.myApp.bean.security.UserRoles;
import it.altran.springmvc.myApp.controller.formbean.InsertClientFormBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;







import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClientDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//--------------------------------------------
	// Esegue le seguenti insert:
	// 1)users   (tabella di spring security)
	// 2)user_roles (tabella di spring security)
	// 3)cliente
	//
	//--------------------------------------------
	
	@Transactional
	public  void insertClient(InsertClientFormBean bean){
		

		
		User user = new User();
		user.setUsername(bean.getUsername());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashPassword = encoder.encode(bean.getPassword());
		user.setPassword(hashPassword);
		user.setEnabled(1);
		
		
		UserRoles role = new UserRoles();
		role.setUser(user);
		role.setUsername(bean.getUsername());
		
		
		/*
		 * hash.put("1", "ROLE_USER");
		hash.put("2", "ROLE_ADMIN");
		
		 */
		int ruolo = Integer.parseInt(bean.getRoleSelezionato());
		if (ruolo==2){
		role.setRole("ROLE_ADMIN");
		}else role.setRole("ROLE_USER");
		
		
		
		ArrayList<UserRoles> lista = new ArrayList<UserRoles>();
		lista.add(role);
		
		user.setListaUserRoles(lista);
		
		entityManager.persist(user);
		
		
		Cliente cliente = new Cliente();
		cliente.setCognome(bean.getSurname());
		cliente.setNome(bean.getName());
		cliente.setUsername(bean.getUsername());
		cliente.setDataInserimento(new Date());
		cliente.setDataAggiornamneto(new Date());
		
		
		entityManager.persist(cliente);
		
		
		
		
		
		
		
	}
	
	

}
