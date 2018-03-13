package it.altran.springmvc.myApp.controller;

import java.util.Map;

import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.PropertiesTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrepareInsertController {
	
	@Autowired
	private PropertiesTable propertiesTable;

	@RequestMapping("/prepareinsert.do")
	public String goToInsertPage(@ModelAttribute("insertFormBean") SearchBean searchBean){
		System.out.println("PrepareInsertController...............");

		//---------------------------------------
		// l'input @ModelAttribute("insertFormBean") 
		// porta l'esecuzione del metodo con l'annotation  @ModelAttribute("insertFormBean") 
		//---------------------------------------
		return "insert";
	}
	
	/**
	 * secondo metodo per testare le transazioni
	 * 
	 * @param searchBean
	 * @return
	 */
	@RequestMapping("/prepareinsert3.do")
	public String goToInsertPage2(@ModelAttribute("insertFormBean") SearchBean searchBean){
		System.out.println("PrepareInsertController.......insert TRE .");

		//---------------------------------------
		// l'input @ModelAttribute("insertFormBean") 
		// porta l'esecuzione del metodo con l'annotation  @ModelAttribute("insertFormBean") 
		//---------------------------------------
		return "insert2";
	}
	
	@RequestMapping("/prepareinsert2.do")
	public @ResponseBody Map<String, String> goToSearchPage2(@RequestParam(value="categoriaSelezionata" ) String categoriaSelezionata,
			@ModelAttribute("searchFormBean") SearchBean searchBean){
		System.out.println("PrepareInsertController2...............");
		
		System.out.println("AJAX>>>>>>>>>>>categoria selezionata:"+categoriaSelezionata);
		

		 Map<String, String> listaGeni =propertiesTable.getGenere(categoriaSelezionata);
		
	
		return listaGeni;
	}
	
	
	
	
	@ModelAttribute("insertFormBean")
	public SearchBean populateSearchForm(){
		System.out.println("PrepareInsertController populateSearchForm....");
		
		SearchBean searchBean = new SearchBean();

	
		//---------------------------
		//POPOLAMENTO SELECT GENERI
		// da Singleton propertiesTable
		//---------------------------
		searchBean.setListaGeneri(propertiesTable.getListaGeneri());
		searchBean.setGenereSel("0");
		
		searchBean.setListaTipologiaGeneri(propertiesTable.getListaTipologiaPubblicazione());
		searchBean.setTipologiaSel("1");
		
		
		
		
		return searchBean;
		
	}
}
