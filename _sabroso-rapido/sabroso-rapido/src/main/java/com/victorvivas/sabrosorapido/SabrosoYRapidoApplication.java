package com.victorvivas.sabrosorapido;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SabrosoYRapidoApplication {
    private static final Logger LOOGER = LoggerFactory.getLogger(SabrosoYRapidoApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SabrosoYRapidoApplication.class, args);
        LOOGER.info("Aplicacion Sabroso&Rapido corriendo en el PUERTO http://localhost:8081");

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
