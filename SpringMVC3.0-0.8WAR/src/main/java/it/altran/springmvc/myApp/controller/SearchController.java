package it.altran.springmvc.myApp.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.bean.Pubblicazione;
import it.altran.springmvc.myApp.bean.PubblicazioneAutore;
import it.altran.springmvc.myApp.controller.formbean.ResultBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.PubblicazioneDAO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search.do")
public class SearchController{
	
	@Autowired
	private PubblicazioneDAO dao;
	
	private Logger loggerInfo = Logger.getLogger("MYLOG");
	
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ModelAndView goToSearchPage(@ModelAttribute("searchFormBean") SearchBean searchBean) {
		
		System.out.println("SearchController...............");
		loggerInfo.info("SearchController!!!!!");
		
		//TODO:Only for test
		System.out.println("searchBean autore:"+searchBean.getAutore());
		System.out.println("searchBean genere sel:"+searchBean.getGenereSel());
		System.out.println("searchBean titolo sel:"+searchBean.getTitolo());
		
		//effettuo ricerca
//		PubblicazioneDAO dao = new PubblicazioneDAO();
		List<Pubblicazione> listaPubblicazione = dao.getPubblicazione(searchBean.getTitolo(), searchBean.getAutore());
		
		
		ArrayList<ResultBean> list = new ArrayList<ResultBean>();
		
		for(int i=0; i < listaPubblicazione.size(); i++){
			
			Pubblicazione pubblicazione = listaPubblicazione.get(i);
			
			ResultBean resultBean = new ResultBean();
			

			resultBean.setCodice(""+pubblicazione.getIdPubblicazione());
			resultBean.setTitolo(pubblicazione.getTitolo());
			resultBean.setEditore("");
			
			Collection<PubblicazioneAutore> listaA = pubblicazione.getListaAutori();
			Iterator<PubblicazioneAutore> iterator = listaA.iterator();
			
			String stringaAutori="";
			int index = 0;
			while(iterator.hasNext()){
				PubblicazioneAutore pubblicazioneAutore = iterator.next();
				Autore autore = pubblicazioneAutore.getAutore();
				if (index==0)
					stringaAutori+= autore.getNome()+" "+autore.getCognome();
				else 
					stringaAutori+=" - "+autore.getNome()+" "+autore.getCognome();
				index++;
			}
			
			resultBean.setAutore(stringaAutori);
			list.add(resultBean);
		}
		
//		ResultBean resultBean = new ResultBean();
//		resultBean.setAutore("UMBERTO ECO");
//		resultBean.setCodice("000012");
//		resultBean.setTitolo("IL NOME DELLA ROSA");
//		resultBean.setEditore("MONDADORI");
//		
//		ResultBean resultBean2 = new ResultBean();
//		resultBean2.setAutore("UMBERTO ECO");
//		resultBean2.setCodice("000011");
//		resultBean2.setTitolo("IL PENDOLO DI FOUCAULT");
//		resultBean2.setEditore("MONDADORI");
//		
//		ResultBean resultBean3 = new ResultBean();
//		resultBean3.setAutore("UMBERTO ECO");
//		resultBean3.setCodice("000013");
//		resultBean3.setTitolo("L'ISOLA DEL GIORNO PRIMA");
//		resultBean3.setEditore("MONDADORI");
//		
//		list.add(resultBean);
//		list.add(resultBean2);
//		list.add(resultBean3);
		
		return new ModelAndView("resultsearch","resultList",list);
	}

	
	

}
