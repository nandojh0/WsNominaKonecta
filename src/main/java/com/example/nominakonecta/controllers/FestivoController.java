/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.controllers;

import com.example.nominakonecta.models.Festivo;
import com.example.nominakonecta.models.FestivoRequest;
import com.example.nominakonecta.services.FestivoService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nando
 */
@RestController
@RequestMapping("/api/festivos")
@AllArgsConstructor
public class FestivoController {
    
    private final FestivoService festivoService;
    
    // Obtener todos los festivos
    @GetMapping
    public ResponseEntity<List<LocalDate>> obtenerFestivos() {
        List<LocalDate> festivos = festivoService.obtenerFestivos();
        return new ResponseEntity<>(festivos, HttpStatus.OK);
    }

    // Crear un nuevo festivo
    @PostMapping
    public ResponseEntity<Festivo> crearFestivo(@RequestBody FestivoRequest request) {
        Festivo nuevoFestivo = festivoService.guardarFestivo(request.getFecha());
        return new ResponseEntity<>(nuevoFestivo, HttpStatus.CREATED);
    }
    
    // Eliminar un festivo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFestivo(@PathVariable Long id) {
        festivoService.eliminarFestivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  
}
