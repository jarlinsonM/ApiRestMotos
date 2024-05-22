package com.apirest2.backends2.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.apirest2.backends2.models.Moto;
import com.apirest2.backends2.repositories.MotoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/motos")

public class MotoController {
    @Autowired
    private MotoRepository motoRepository;

    @GetMapping
    public List <Moto> getAllMotos(){
        return motoRepository.findAll();
    }
    @GetMapping("/{id}")
    public Moto getMotosbyId(@PathVariable Long id){
        return motoRepository.findById(id).orElse(null);
    }
    @PostMapping
    public Moto crearMoto(@RequestBody Moto moto){
        return motoRepository.save(moto);
    }
    @PutMapping("/{id}")
    public Moto updateMoto(@PathVariable Long id, @RequestBody Moto moto){
        moto.setId(id);
        return motoRepository.save(moto);
    }
    @DeleteMapping ("/{id}")
    public void deleteMoto(@PathVariable Long id){
        motoRepository.deleteById(id);
    }

}

    


