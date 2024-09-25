/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.repository;

import com.example.nominakonecta.models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author nando
 */
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    
}
