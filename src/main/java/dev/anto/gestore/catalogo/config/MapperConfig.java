package dev.anto.gestore.catalogo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper mapper (){
        var mapper = new ModelMapper();

        //personalizzazione mapper

        return mapper;
    }

}
