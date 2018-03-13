package it.altran.springmvc.myApp.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;






//import javax.transaction.Transactional;
import javax.validation.Valid;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.controller.formbean.ErrorBean;
import it.altran.springmvc.myApp.controller.formbean.InsertBean;
import it.altran.springmvc.myApp.controller.formbean.InsertBean2;
import it.altran.springmvc.myApp.controller.formbean.SearchAuthorBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.AuthorDAO;
import it.altran.springmvc.myApp.dao.PropertiesTable;
import it.altran.springmvc.myApp.dao.PubblicazioneDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope("session")
@Transactional
public class InsertController2 {
	
	@Autowired
	private PubblicazioneDAO pubblicazioneDAO;
	
	@Autowired
	private AuthorDAO authorDAO;

	
	@Autowired
	private PropertiesTable propertiesTable;

	//bean che viene messo in sessione
	private InsertBean2 bean = new InsertBean2();
	
	@RequestMapping("/insert2.do")
	public ModelAndView goToSummaryPage(
			@Valid @ModelAttribute("insertFormBean") SearchBean insertFormBean,
			BindingResult result,
			@RequestParam Map<String,String> allRequestparams){
		
		
		
		//----------------------------------------------------------------------------
		//Prendo alcuni parametri dalla request
		// Gli autori sono in campi hidden creati da jquery: non li trovo 
		// nell'oggetto SearchBean insertFormBean
		//--------------------------------------------------------------------------
		
		//metto in sessione i dati e vado alla pagina di rassunto
		Iterator<String>  iterator =  allRequestparams.keySet().iterator();
			
		ArrayList<String> autori = new ArrayList<>();
		while(iterator.hasNext()){
			String key = iterator.next();
			String value =allRequestparams.get(key);
			System.out.println(" "+key+"-"+value);
			if (key.equalsIgnoreCase("titolo"))
				bean.setTitolo(value);
			else if (key.equalsIgnoreCase("genereSel2"))
				bean.setGenereSelezionato(value);
//			else if (key.startsWith("hidden"))
//				autori.add(value);
			else if (key.equalsIgnoreCase("tipologiaSel"))
				bean.setTipologiaSelezionato(value);
			else if (key.equalsIgnoreCase("nomeAutore"))
				bean.setNomeAutore(value);
		    else if (key.equalsIgnoreCase("cognomeAutore"))
					bean.setCognomeAutore(value);
			
		}
		
		
		
		//----------------------------------------------
		// Se ci sono errori in validazione @Valid
		//  (vedi il codice del bean SearchBean)
		//----------------------------------------------
		if(result.hasErrors()){
			
			
			riPopolaDopoErroriInValidazione(insertFormBean);
			
			
			//-------------------------------------------------------------------
//			// INIZIO: Devo ripopolare ANCHE la lista degli autori della pagina insert.jsp
//			//  Ho solo l'elenco degli idAutori selezionati nella request: 
//			//   Devo rifare le query!!
//			//-------------------------------------------------------------------
//			Map<String, String> map = new HashMap<String, String>();
//					
//			for(int i=0; i< autori.size(); i++){
//				String id = autori.get(i);
//				Autore autore = authorDAO.getAutoreFromID(id);
//				System.out.println(">>>>>>>>>>>MAP:"+autore.getIdAutore()+ autore.getCognome());
//				map.put(""+autore.getIdAutore(), autore.getNome()+" "+autore.getCognome());
//			}
//			insertFormBean.setListaMyAutori(map);
			//--------
			//FINE
			//--------
			
			return new ModelAndView("insert2","InsertBean",insertFormBean);

		}
		
		return new ModelAndView("riassuntoInserimento2","bean",bean);

	}
	
	private void riPopolaDopoErroriInValidazione(SearchBean insertFormBean){
		//---------------------------------------------------
		// Devo ricaricare le liste dei generi e tipologia 
		//---------------------------------------------------
		insertFormBean.setListaGeneri(propertiesTable.getListaGeneri());
		insertFormBean.setListaTipologiaGeneri(propertiesTable.getListaTipologiaPubblicazione());
		
		//-------------------------------------------------------
		// riprendo le scelte effettuate (se sono presenti)
		//-------------------------------------------------------
		String categoriaSelezionata = insertFormBean.getGenereSel();
		String genereSelezionato = insertFormBean.getGenereSel2();
		String tipologiaSelezionata = insertFormBean.getTipologiaSel();
		
		//TODO: Only for test
		System.out.println(">>>>>>>>>>>categoriaSelezionata:"+categoriaSelezionata);
		System.out.println(">>>>>>>>>>>genereSelezionato:"+genereSelezionato);
		System.out.println(">>>>>>>>>>>tipologiaSelezionata:"+tipologiaSelezionata);
		
		if (  (categoriaSelezionata!=null)&&(!categoriaSelezionata.equals(""))&&(!categoriaSelezionata.equals("0"))   ){
			
			insertFormBean.setGenereSel(categoriaSelezionata);
			insertFormBean.setListaGeneri2(propertiesTable.getGenere(categoriaSelezionata));
			
			
		}
		if (  (tipologiaSelezionata!=null)&&(!tipologiaSelezionata.equals(""))   ){
			insertFormBean.setTipologiaSel(tipologiaSelezionata);
		}
		
		
	}

	/**
	 * 
	 * La transazione funziona mettendo anche il Controller @transactional.
	 * 
	 * ho provato con un errore dopo la insert del autore settando id a 99999
	 * Viene tutto rollbackato
	 * 
	 * @param allRequestparams
	 * @return
	 */
	@RequestMapping("/insertDB2.do")
	public String insesrtDB(
			@RequestParam Map<String,String> allRequestparams){
		
		//TODO:Only for test
		System.out.println(">>>>>>>>>>>INSERIMENTO AUTORE "+bean.getCognomeAutore()+" "+bean.getNomeAutore());
		
		int autoreid =authorDAO.insertAutore(bean);
		
		//------------------------------------------------------------------------------------------
		//INIZIO TEST TRANSACTION 
		// setto id autore ad un numero inestistente che viola la constraint di PUBBLICAZIONE_AUTORE
		//-------------------------------------------------------------------------------------------
		
//		autoreid=9999999;
		
		//FINE TEST TRANSACTION 
		
		System.out.println(">>>>>>>>>>>ID AUTORE ritornato:"+autoreid);
		
		System.out.println(">>>>>>>>>>>INSERIMENTO AUTORE "+bean.getTitolo()+" "+bean.getGenereSelezionato()+" "+bean.getTipologiaSelezionato());
		System.out.println(">>>>>>>>>>>INSERIMENTO PUBBLICAZIONE");
		
		pubblicazioneDAO.insertPubblicazione2(bean,autoreid);
		
		return "welcome";
	}
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception e){
		
		ErrorBean errorBean = new ErrorBean();
		errorBean.setCodeError("");
		errorBean.setMessageError(e.getMessage());
		
		String stacktrace="";
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		
		stacktrace=writer.toString();
		
		errorBean.setStackTraceError(stacktrace);
		ModelAndView model = new ModelAndView("error","errorbean",errorBean);
		
		model.addObject("mytitle", "ERROR!");
		return model;
		
	}
	
	
}
