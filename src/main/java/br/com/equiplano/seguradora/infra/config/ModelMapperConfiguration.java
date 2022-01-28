package br.com.equiplano.seguradora.infra.config;

import java.time.ZoneId;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jdk8.Jdk8Module;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.equiplano.seguradora.application.buscadorapolice.dto.response.v1.BuscadorApoliceResponse;
import br.com.equiplano.seguradora.application.cliente.dto.request.v1.ClienteRequest;
import br.com.equiplano.seguradora.application.cliente.dto.response.v1.ClienteResponse;
import br.com.equiplano.seguradora.core.domain.entity.Apolice;
import br.com.equiplano.seguradora.core.domain.entity.Cliente;

@Configuration
public class ModelMapperConfiguration {

	@Bean
    public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.registerModule(buildModuleConfig());
		modelMapper.registerModule(new Jdk8Module());
		
		buildClienteToClienteDTO(modelMapper);
		buildClienteDtoToCliente(modelMapper);
		
        return modelMapper;
    }

	private void buildClienteToClienteDTO(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<Cliente, ClienteResponse>() {
		    protected void configure() {
		    	map().setUf(source.getUf().getDescricao());
		        map().setCpf(source.getCpf().getNumeroCpf());
		    }
		});	
	}

	private void buildClienteDtoToCliente(ModelMapper modelMapper) {
		
		modelMapper.addMappings(new PropertyMap<ClienteRequest, Cliente>() {
			protected void configure() {
				map().getCpf().setNumeroCpf(source.getCpf());
				map().getUf().setDescricao(source.getUf());
			}
		});
	}
	
	private Jsr310Module buildModuleConfig() {
		Jsr310ModuleConfig config  = new Jsr310ModuleConfig("dd/MM/yyyy", "HH:mm:ss", "HH:mm:ss", ZoneId.systemDefault());
		Jsr310Module jsr310Module = new Jsr310Module(config);
		return jsr310Module;
	}

}
