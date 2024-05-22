package com.apirest2.backends2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="detalles")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Detalles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_detalle;
    private String referencia;
    private String id_moto;
    private String color;
    private long precio;


}
