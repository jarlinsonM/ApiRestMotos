package com.apirest2.backends2.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.apirest2.backends2.models.Detalles;
import com.apirest2.backends2.models.Moto;
import com.apirest2.backends2.repositories.DetallesRepository;
import com.apirest2.backends2.repositories.MotoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ControllerDetalles.class)
public class DetallesControllerTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private DetallesRepository detallesRepository;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void testGetAllDetalles() throws Exception {
        when(detallesRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/motos/detalles"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
        verify(detallesRepository, times(1)).findAll();
    }
    
    @Test
    public void testGetDetalleById() throws Exception {
        Detalles detalle = new Detalles();
        detalle.setId_detalle(1L);
        when(detallesRepository.findById(1L))
        .thenReturn(Optional.of(detalle));
        mockMvc.perform(get("/api/motos/detalles/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id_detalle").value(1));
        verify(detallesRepository, times(1)).findById(1L);                  
    }
    
    @Test
    public void testCreateDetalle() throws Exception{
        Detalles detalle = new Detalles();
        detalle.setId_detalle(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String detallesjson = objectMapper.writeValueAsString(detalle);
        when(detallesRepository.save(any(Detalles.class))).thenReturn(detalle);

        mockMvc.perform(post("/api/motos/detalles")).
            .contentType(MediaType.APPLICATION_JSON)
            .content(detallesjson)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id_detalle").value(1L))
            .andExpect(jsonPath("$.referencia").isEmpty())
            .andExpect(jsonPath("$.id_moto").isEmpty())
            .andExpect(jsonPath("$.color").isEmpty())
            .andExpect(jsonPath("$.precio").isEmpty());
        verify(detallesRepository, times(1)).save(any(Detalles.class));
    }

    @Test
    public void testUpdateDetalle() throws Exception {
        Detalles detalle = new Detalles();
        detalle.setId_detalle(1L);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String detallesjson = objectMapper.writeValueAsString(detalle);

        when(detallesRepository.save(any(Detalles.class))).thenReturn(detalle);

        mockMvc.perform(put("/api/motos/detalles/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(detallesjson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.marca").isEmpty())
            .andExpect(jsonPath("$.modelo").isEmpty())
            .andExpect(jsonPath("$.cilindraje").isEmpty())
            .andExpect(jsonPath("$.placa").isEmpty());
            

        verify(detallesRepository, times(1)).save(any(Detalles.class));
    }

    @Test
    public void testDeleteDetalle() throws Exception {
        doNothing().when(detallesRepository).deleteById(1L);
        mockMvc.perform(delete("/api/motos/detalles/1"))
        .andExpect(status().isNoContent());
        verify(detallesRepository, times(1)).deleteById(1L);

    }
    
    
}

   

