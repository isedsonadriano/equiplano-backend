package br.com.equiplano.seguradora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableFeignClients
@Slf4j
public class App extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	  
	public static void main(String[] args) {
		Environment env = SpringApplication.run(App.class, args).getEnvironment();
		log.info("Serviço iniciado");
        log.info("Documentação das APIs: http://localhost:{}/swagger-ui.html", env.getProperty("server.port"));
	}

}
