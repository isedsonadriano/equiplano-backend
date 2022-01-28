package br.com.equiplano.seguradora.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.equiplano.seguradora.core.service.ClienteService;
import br.com.equiplano.seguradora.core.service.implementation.ClienteServiceImpl;

@Configuration
public class ClienteServiceConfiguration {

	@Bean
    public ClienteService modelMapper() {
		return new ClienteServiceImpl();
    }

	
}
