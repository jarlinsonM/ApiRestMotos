package com.apirest2.backends2.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.apirest2.backends2.models.Moto;
import com.apirest2.backends2.repositories.MotoRepository;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/motos")

public class MotoController extends ApiBaseController{
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
    /*
    @PostMapping
    public Moto crearMoto(@RequestBody Moto moto){
        return motoRepository.save(moto);
    }*/
    
    @PostMapping 
    public ResponseEntity<Moto> crearMoto(@RequestBody Moto moto){
         Moto motoGuardada = motoRepository.save(moto); 
         return ResponseEntity.
         created(URI.create("http://localhost/api/motos/" + motoGuardada.getId()))
         .body(motoGuardada);
    }
    /*
     * @PutMapping("/{id}")
        public Moto updateMoto(@PathVariable Long id, @RequestBody Moto moto){
            moto.setId(id);
            return motoRepository.save(moto);
        }
    */
    

    @PutMapping("/{id}") 
    public ResponseEntity<Moto> actualizarMoto(@PathVariable Long id, @RequestBody Moto moto){ 
        Moto motoActualizada = motoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Moto no encontrada"));
        motoActualizada.setMarca(moto.getMarca()); 
        motoActualizada.setModelo(moto.getModelo());
        motoActualizada.setCilindraje(moto.getCilindraje()); 
        motoActualizada.setPlaca(moto.getPlaca()); 
        return ResponseEntity.ok(motoRepository.save(motoActualizada)); 
    }

    /*@DeleteMapping ("/{id}")
        public void deleteMoto(@PathVariable Long id){
            motoRepository.deleteById(id);
        }
    */

    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> borrarMoto(@PathVariable Long id){ 
        motoRepository.deleteById(id); 
        return ResponseEntity.noContent().build(); 
    }

}

    


