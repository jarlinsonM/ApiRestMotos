package com.apirest2.backends2.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.context.WebApplicationContext;

import com.apirest2.backends2.models.Moto; 
import com.apirest2.backends2.repositories.MotoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

//import io.swagger.v3.oas.models.media.MediaType;


@WebMvcTest(MotoController.class)
public class MotoControllerTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private MotoRepository motoRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void testGetAllMotos() throws Exception {
        when(motoRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/motos"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
        verify(motoRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetMotoById() throws Exception {
        Moto moto = new Moto();
        moto.setId(1L);
        when(motoRepository.findById(1L))
        .thenReturn(Optional.of(moto));
        mockMvc.perform(get("/api/motos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1));
        verify(motoRepository, times(1)).findById(1L);                  
    }
    /*
     * @Test
    public void testCreateMoto() throws Exception {
        Moto moto = new Moto();
        moto.setId(1L);
        when(motoRepository.save(any(Moto.class))).thenReturn(moto);
        mockMvc.perform(post("/api/motos"))
            //.contentType(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": null, \"marca\": null, \"modelo\" : null, \"cilindraje\" : null, \"placa\" : null}")
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.marca").isEmpty())
            .andExpect(jsonPath("$.modelo").isEmpty())
            .andExpect(jsonPath("$.cilindraje").isEmpty())
            .andExpect(jsonPath("$.placa").isEmpty());
        verify(motoRepository, times(1)).save(any(Moto.class));
    }
     */
    @Test
    public void testCreateMoto() throws Exception{
        Moto moto = new Moto();
        moto.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String motojson = objectMapper.writeValueAsString(moto);
        when(motoRepository.save(any(Moto.class))).thenReturn(moto);

        mockMvc.perform(post("/api/motos")).
            .contentType(MediaType.APPLICATION_JSON)
            .content(motojson)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.marca").isEmpty())
            .andExpect(jsonPath("$.modelo").isEmpty())
            .andExpect(jsonPath("$.cilindraje").isEmpty())
            .andExpect(jsonPath("$.placa").isEmpty());
        verify(motoRepository, times(1)).save(any(Moto.class));
    }

    @Test
    public void testUpdateMoto() throws Exception {
       Moto moto = new Moto();
       moto.setId(1L);
        

        ObjectMapper objectMapper = new ObjectMapper();
        String motojson = objectMapper.writeValueAsString(moto);

        when(motoRepository.save(any(Moto.class))).thenReturn(moto);

        mockMvc.perform(put("/api/motos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(motojson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.marca").isEmpty())
            .andExpect(jsonPath("$.modelo").isEmpty())
            .andExpect(jsonPath("$.cilindraje").isEmpty())
            .andExpect(jsonPath("$.placa").isEmpty());
            

        verify(motoRepository, times(1)).save(any(Moto.class));
    }

    @Test
    public void testDeleteMoto() throws Exception {
        doNothing().when(motoRepository).deleteById(1L);
        mockMvc.perform(delete("/api/motos/1"))
        .andExpect(status().isNoContent());
        verify(motoRepository, times(1)).deleteById(1L);

    }
    
    
}

   