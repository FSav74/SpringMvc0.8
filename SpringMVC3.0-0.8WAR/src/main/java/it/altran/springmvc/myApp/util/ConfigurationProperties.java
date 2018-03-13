package it.altran.springmvc.myApp.util;

import it.altran.springmvc.myApp.util.exception.ConfigurationException;

import java.util.Enumeration;
import java.util.Properties;











import javax.annotation.PostConstruct;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationProperties extends ConfigurationCostant{
	
	@Autowired
	private Properties configuration;
	

	public ConfigurationProperties(){
//		test();   /*Errato : usare @PostConstruct per usare oggetti injected*/
	}
	
	/*
	 * Because Spring can't access any fields or methods before the object is created.
	 * 
	 * 
	 */
	@PostConstruct
	public void init() throws ConfigurationException{
		test();
		
		DOMConfigurator.configure(getProperty(LOG_CONFIGURATION));

		
	}
	
	public String getProperty(String paramName) throws ConfigurationException{
		
		if ((paramName==null)||(paramName.equals(""))) throw new ConfigurationException(" Nome Parametro nullo  o vuoto !");
		
		String value =  configuration.getProperty(paramName);
		
		if ((value==null)||(value.equals(""))) throw new ConfigurationException("Parametro "+paramName+" non trovato nel file di configurazione");
		
		return value;
		
	}	
	
	private void  test(){
		System.out.println("-----------------------------------");
		Enumeration<Object> enum1 = configuration.keys();
		while (enum1.hasMoreElements()){
			
			String key = (String) enum1.nextElement();
			String value = (String) configuration.get(key);
			System.out.println("key = "+key+"- value = "+value);
		}
				
		System.out.println("-----------------------------------");
	}
	
}
