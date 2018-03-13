package it.altran.springmvc.myApp.controller;

import java.util.HashMap;

import it.altran.springmvc.myApp.controller.formbean.InsertClientFormBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@Secured("ROLE_ADMIN")
	@RequestMapping("/administration.do")
	public String goToInsertClienthPage(@ModelAttribute("insertClientFormBean") InsertClientFormBean bean ){
		System.out.println("AdminController.........");
		System.out.println(">>>>ONLY FOR ADMIN_ROLE USER<<<<<");
		
		

		
		
		return "adminInsertClient";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/secondAdmin.do")
	public String goToSearchPage2(){
		System.out.println("AdminController link nascosto...............");

		
		
		return "admin";
	}
	
	
	@ModelAttribute("insertClientFormBean")
	public InsertClientFormBean populateSearchForm(){

		System.out.println(">>InsertClientFormBean  populateSearchForm....");
		
		InsertClientFormBean bean = new InsertClientFormBean();

		//TEST
		HashMap<String , String> hash = new HashMap<String , String> ();
		hash.put("1", "ROLE_USER");
		hash.put("2", "ROLE_ADMIN");
		
		
		
		bean.setListaRuoli(hash);
		
		return bean;
		
	}
	
	
	
}
