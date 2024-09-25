/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import com.example.nominakonecta.models.Persona;
import com.example.nominakonecta.repository.PersonaRepository;
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
public class PersonaService {
    
   private final PersonaRepository personaRepository;

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }
    
}
