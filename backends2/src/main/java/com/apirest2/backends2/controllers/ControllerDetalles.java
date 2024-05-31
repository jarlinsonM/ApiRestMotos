package com.apirest2.backends2.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.apirest2.backends2.models.Detalles;
import com.apirest2.backends2.repositories.DetallesRepository;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/motos/detalles")

public class ControllerDetalles extends ApiBaseController {
    @Autowired
    private DetallesRepository detallesRepository;

    @GetMapping
    public List<Detalles> getAllDesatalles(){
        return detallesRepository.findAll();
    }

    @GetMapping("/{id_detalle}")
    public Detalles getDetallebyId(@PathVariable Long id_moto){
        return detallesRepository.findById(id_moto).orElse(null);
    }

   /*
        @PostMapping
        public Detalles createDetalle(@RequestBody Detalles detalle){
            return detallesRepository.save(detalle);
        }
    */ 

    @PostMapping 
    public ResponseEntity<Detalles> crearMoto(@RequestBody Detalles detalle){
         Detalles detalleGuardado = detallesRepository.save(detalle); 
         return ResponseEntity.
         created(URI.create("http://localhost/api/motos/detalles" + detalleGuardado.getId_detalle()))
         .body(detalleGuardado);
    }
    /*
     * @PutMapping("/{id_detalle}")
        public Detalles updateDetalle(@PathVariable Long id_detalle, @RequestBody Detalles detalle){
            detalle.setId_detalle(id_detalle);
            return detallesRepository.save(detalle);
        
        }
    */
    @PutMapping("/{id_detalle}") 
    public ResponseEntity<Detalles> updateDetalle(@PathVariable Long id_detalle, @RequestBody  Detalles detalle){ 
        Detalles detalleactualizado = detallesRepository.findById(id_detalle).orElseThrow();
        detalleactualizado.setReferencia(detalle.getReferencia());
        detalleactualizado.setId_moto(detalle.getId_moto());
        detalleactualizado.setColor(detalle.getColor());
        detalleactualizado.setPrecio(detalle.getPrecio());
        return ResponseEntity.ok(detallesRepository.save(detalleactualizado)); 
    }

    /*
     * @DeleteMapping ("/{id_detalle}")
        public void deleteDetalle(@PathVariable Long id_detalle){
            detallesRepository.deleteById(id_detalle);
        }
    */

    @DeleteMapping("/{id_detalle}") 
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id_detalle){ 
        detallesRepository.deleteById(id_detalle); 
        return ResponseEntity.noContent().build(); 
    }
    

}
