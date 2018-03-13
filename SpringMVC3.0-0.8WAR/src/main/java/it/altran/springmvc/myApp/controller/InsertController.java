package it.altran.springmvc.myApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import it.altran.springmvc.myApp.bean.Autore;
import it.altran.springmvc.myApp.controller.formbean.InsertBean;
import it.altran.springmvc.myApp.controller.formbean.SearchAuthorBean;
import it.altran.springmvc.myApp.controller.formbean.SearchBean;
import it.altran.springmvc.myApp.dao.AuthorDAO;
import it.altran.springmvc.myApp.dao.PropertiesTable;
import it.altran.springmvc.myApp.dao.PubblicazioneDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope("session")
public class InsertController {
	
	@Autowired
	private PubblicazioneDAO pubblicazioneDAO;
	
	@Autowired
	private AuthorDAO authorDAO;
	
	@Autowired
	private PropertiesTable propertiesTable;

	//bean che viene messo in sessione
	private InsertBean bean = new InsertBean();
	
	@RequestMapping("/insert.do")
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
			else if (key.startsWith("hidden"))
				autori.add(value);
			else if (key.equalsIgnoreCase("tipologiaSel"))
				bean.setTipologiaSelezionato(value);
			
			
		}

		bean.setAutori(autori);
		
		
		
		//----------------------------------------
		// Check se sono selezionati gli autori
		//----------------------------------------
		if (  (autori==null)||(autori.size()==0)  ){
			
			riPopolaDopoErroriInValidazione(insertFormBean);
			result.rejectValue("autore", "insertFormBean", "Autore è un campo obbligatorio");
			return new ModelAndView("insert","InsertBean",insertFormBean);
		}
		
		
		//----------------------------------------------
		// Se ci sono errori in validazione @Valid
		//  (vedi il codice del bean SearchBean)
		//----------------------------------------------
		if(result.hasErrors()){
			
			
			riPopolaDopoErroriInValidazione(insertFormBean);
			
			
			//-------------------------------------------------------------------
			// INIZIO: Devo ripopolare ANCHE la lista degli autori della pagina insert.jsp
			//  Ho solo l'elenco degli idAutori selezionati nella request: 
			//   Devo rifare le query!!
			//-------------------------------------------------------------------
			Map<String, String> map = new HashMap<String, String>();
					
			for(int i=0; i< autori.size(); i++){
				String id = autori.get(i);
				Autore autore = authorDAO.getAutoreFromID(id);
				System.out.println(">>>>>>>>>>>MAP:"+autore.getIdAutore()+ autore.getCognome());
				map.put(""+autore.getIdAutore(), autore.getNome()+" "+autore.getCognome());
			}
			insertFormBean.setListaMyAutori(map);
			//--------
			//FINE
			//--------
			
			return new ModelAndView("insert","InsertBean",insertFormBean);

		}
		
		return new ModelAndView("riassuntoInserimento","bean",bean);

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

	
	@RequestMapping("/insertDB.do")
	public String insesrtDB(
			@RequestParam Map<String,String> allRequestparams){
		
		//TODO:Only for test
		System.out.println(">>>>>>>>>>>INSERIMENTO DB:"+bean.getTitolo());
		
		pubblicazioneDAO.insertPubblicazione(bean);
		
		return "welcome";
	}
}
