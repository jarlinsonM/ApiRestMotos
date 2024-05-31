package com.apirest2.backends2.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition( 
    info = @Info(
        title= "Motos",
        version = "1.0",
        description= "API para gerenciar motos"

    ),
    tags = {
        @Tag(
            name = "Controlador base",
            description = "Controlador base para a API, este controlsdor se estendera a todos lod endpoints"
        )
    }

)
public class ApiBaseController {
    
}
