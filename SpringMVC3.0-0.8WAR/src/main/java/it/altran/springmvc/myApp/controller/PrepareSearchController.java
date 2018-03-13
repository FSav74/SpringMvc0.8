package it.altran.springmvc.myApp.controller;


import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.PropertiesTable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller

public class PrepareSearchController {
	
	
	@Autowired
	private PropertiesTable propertiesTable;
	
	@RequestMapping("/preparesearch.do")
	public String goToSearchPage(@ModelAttribute("searchFormBean") SearchBean searchBean){
		System.out.println("PrepareSearchController...............");

		//---------------------------------------
		// l'input @ModelAttribute("searchFormBean") 
		// porta l'esecuzione del metodo con l'annotation  @ModelAttribute("searchFormBean") .
		
		// NON E' ESATTO!!!: il metodo con @ModelAttribute("searchFormBean") viene eseguito sempre per primo.
		// MA il model Attribute viene inserito nella request solo alla fine dell'esecuzione del Controller:
		// Quindi Per avere il modelAttribute in questo metodo devo metterlo nei parqametri 
		//---------------------------------------
		return "search";
	}
	
	
	@RequestMapping("/preparesearch2.do")
	public String goToSearchPage2(@RequestParam(value="genereSel" ) String genereselezionato,
			@ModelAttribute("searchFormBean") SearchBean searchBean){
		System.out.println("PrepareSearchController2...............");
		
		System.out.println(">>>>><categoria selezionata:"+genereselezionato);
		

		searchBean.setGenereSel(genereselezionato);
		
		if (  (genereselezionato!=null)&&(!genereselezionato.equals("0"))  ){
			searchBean.setListaGeneri2(propertiesTable.getGenere(genereselezionato));
		}
		
	
		return "search";
	}
	
	
	@ModelAttribute("searchFormBean")
	public SearchBean populateSearchForm(){
		System.out.println("PrepareSearchController populateSearchForm....");
		
		SearchBean searchBean = new SearchBean();

		//TEST
		//searchBean.setAutore("UMBERTO ECO");
		
		
	
		//---------------------------
		//POPOLAMENTO SELECT GENERI
		// da Singleton propertiesTable
		//---------------------------
		searchBean.setListaGeneri(propertiesTable.getListaGeneri());
		searchBean.setGenereSel("0");
		
		return searchBean;
		
	}
}
