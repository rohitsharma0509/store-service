package com.app.ecom.store.productservice.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.app.ecom.store.productservice.dto.jaxb.ProductsType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class ApplicationConfiguration {
	private JAXBContext jaxbContext;
	
	private JAXBContext getJaxbContext() throws JAXBException {
		if (jaxbContext == null)
			jaxbContext = JAXBContext.newInstance(ProductsType.class);
		return jaxbContext;
	}
	
	@Bean("unmarshaller")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Unmarshaller getUnmarshaller() throws JAXBException {
		return getJaxbContext().createUnmarshaller();
	}

	@Bean("marshaller")
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Marshaller getMarshaller() throws JAXBException {
		Marshaller marshaller = getJaxbContext().createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		return marshaller;
	}
}
