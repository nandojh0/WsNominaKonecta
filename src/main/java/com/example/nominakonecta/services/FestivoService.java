/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import com.example.nominakonecta.models.Festivo;
import com.example.nominakonecta.repository.FestivoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author nando
 */

@AllArgsConstructor
@Service
public class FestivoService {
    
     private final FestivoRepository festivoRepository;
     
     public List<LocalDate> obtenerFestivos() {
        return festivoRepository.findAll().stream()
                                 .map(Festivo::getFecha)
                                 .toList();
    }
     
      // Guardar un nuevo festivo
    public Festivo guardarFestivo(LocalDate fecha) {
       Optional<Festivo> festivoExistente = festivoRepository.findByFecha(fecha);
        if (festivoExistente.isPresent()) {
            throw new IllegalArgumentException("El festivo con fecha " + fecha + " ya existe.");
        }
        Festivo festivo = Festivo.builder().fecha(fecha).build();
        return festivoRepository.save(festivo);
    }
    
    
    
    // Eliminar un festivo por ID
    public void eliminarFestivo(Long id) {
        festivoRepository.deleteById(id);
    }
}
