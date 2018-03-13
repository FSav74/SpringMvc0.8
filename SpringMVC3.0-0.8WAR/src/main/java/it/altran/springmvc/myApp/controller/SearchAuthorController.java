package it.altran.springmvc.myApp.controller;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.controller.formbean.ResultBean;
import it.altran.springmvc.myApp.controller.formbean.SearchAuthorBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.AuthorDAO;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchAuthorController {
	
	private Logger loggerInfo = Logger.getLogger("MYLOG");
	
	@Autowired
	private AuthorDAO dao;
	
	@RequestMapping("/searchAuthor.do")
	public ModelAndView goToSearchPage(
			@RequestParam(value="nome" ) String nome,
			@RequestParam(value="cognome" ) String cognome,
			@ModelAttribute("searchAuthorFormBean") SearchAuthorBean searchBean){
		
		System.out.println("SearchAuthorController...............");
		loggerInfo.info("SearchAuthorController!!!!!");
		
		//TEST
		System.out.println("searchBean autore:"+nome);
		System.out.println("searchBean genere sel:"+cognome);
		
		ArrayList<Autore> lista = (ArrayList<Autore>) dao.getAutore(nome, cognome);
		
		ArrayList<SearchAuthorBean> list = new ArrayList<SearchAuthorBean>();
		
		if(lista!=null)
		for(int i=0; i<lista.size();i++){
			Autore autore = lista.get(i);
			
			SearchAuthorBean resultBean = new SearchAuthorBean();
			resultBean.setNomeAutore(autore.getNome());
			resultBean.setCognomeAutore(autore.getCognome());
			resultBean.setCodice(""+autore.getIdAutore());

			list.add(resultBean);
		}
		
		
		
		searchBean.setNumeroRisultati(""+list.size());

		
		return new ModelAndView("searchAuthorPopUp","resultList",list);
	}
	
}
