package it.altran.springmvc.myApp.controller;


import it.altran.springmvc.myApp.controller.formbean.SearchAuthorBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrepareSearchAuthor {

	@RequestMapping("/preparesearchauthor.do")
	public String goToSearchPage(@ModelAttribute("searchAuthorFormBean") SearchAuthorBean searchBean){
		System.out.println("PrepareSearchAuthorController...............");

		searchBean.setNumeroRisultati("0");
		return "searchAuthorPopUp";
	}
}
