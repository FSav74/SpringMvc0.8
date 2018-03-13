package it.altran.springmvc.myApp.controller;



import javax.annotation.PostConstruct;
import javax.naming.Context;





//import it.altran.springmvc.myApp.locator.ServiceLocator;
//import it.altran.springmvc.myApp.locator.ServiceLocatorException;
import it.altran.springmvc.myApp.util.ConfigurationProperties;
import it.altran.springmvc.myApp.util.exception.ConfigurationException;

import java.util.Properties;

//import javax.ejb.EJB;
//import javax.ejb.EJBException;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@Autowired
	private ConfigurationProperties configurationProperties;
	
	
	
	private Logger loggerInfo = null;
	

	@RequestMapping(value="/index.do",method = RequestMethod.GET)
	public String goToWelcomePage(ModelMap modelMap) throws ConfigurationException{
		System.out.println("WelcomeController...............");
		
		System.out.println("configuration:"+configurationProperties.getProperty(configurationProperties.LOGGER_NAME));
		loggerInfo = Logger.getLogger(configurationProperties.getProperty("LOGGER_NAME"));
		
		
		
		modelMap.addAttribute("message", "Francesco Saverio");
		
		
		
		//test
		System.out.println("configuration:"+configurationProperties.getProperty(configurationProperties.LOG_CONFIGURATION));
		
		return "welcome";
	}
	
	@RequestMapping(value="/login.do",method = RequestMethod.GET)
	public ModelAndView goToLoginPage(ModelMap modelMap,
			@RequestParam(value="error", required=false) String error) throws ConfigurationException{
		System.out.println("******LOGIN********");
		
		ModelAndView modelAndView = new ModelAndView();
		if(error!=null){
			modelAndView.addObject("error", "Invalid username or password!");
		}
			
		modelAndView.setViewName("login");
		
		return modelAndView;
	}

	
}
