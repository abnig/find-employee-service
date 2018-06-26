package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Configuration
public class Config {
	/*
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this is the package name specified in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("spring.poc.soap.client");
        return marshaller;
    }
 
   @Bean
   public SaajSoapMessageFactory messageFactory() {
	   return new SaajSoapMessageFactory();
   }
    
    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
    	WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
    	webServiceTemplate.setMarshaller(marshaller);
    	webServiceTemplate.setUnmarshaller(marshaller);
    	webServiceTemplate.setMessageSender(new HttpComponentsMessageSender());
    	return webServiceTemplate;
    }
    */
}