package it.altran.springmvc.myApp.controller.admin.cliente;

import it.altran.springmvc.myApp.controller.formbean.ErrorBean;
import it.altran.springmvc.myApp.controller.formbean.InsertClientFormBean;




import it.altran.springmvc.myApp.dao.admin.ClientDAO;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class InsertClientController {
	
	@Autowired
	private ClientDAO dao;
	
	@RequestMapping("/insertClient.do")
	public ModelAndView insertClient(
			@Valid @ModelAttribute("insertClientFormBean") InsertClientFormBean bean,
			BindingResult result,
			@RequestParam Map<String,String> allRequestparams){
		
		System.out.println("procedo ad inserire:");
		System.out.println("nome:"+bean.getName());
		System.out.println("cognome:"+bean.getSurname());
		System.out.println("username:"+bean.getUsername());
		System.out.println("password:"+bean.getPassword());
		
		try{
			dao.insertClient(bean);
		}catch(Exception e){
			ErrorBean error = new ErrorBean();
			error.setCodeError("-1");
			error.setMessageError("Errore inserimento Cliente/user/user roles");
			error.setException(e);
			
			return new ModelAndView("error","errorbean",error);
		}
		
		
		
		return new ModelAndView("insertedClientPage","bean",bean);
	}

}
