package com.cdsoft.dialogflowserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static com.cdsoft.dialogflowserver.util.Constants.INTEGRATOR_REST_TEMPLATE;

@Configuration
public class RestConfig {

    @Value("${integrator.url}")
    private String integratorServerUrl;

    @Bean(name = INTEGRATOR_REST_TEMPLATE)
    public RestTemplate getIntegratorRest(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new RootUriTemplateHandler(integratorServerUrl));
        return restTemplate;
    }

}
