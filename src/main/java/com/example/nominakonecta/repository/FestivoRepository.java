/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.nominakonecta.repository;

import com.example.nominakonecta.models.Festivo;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author nando
 */
public interface FestivoRepository  extends JpaRepository<Festivo, Long> {
    // Método para buscar un festivo por fecha
    Optional<Festivo> findByFecha(LocalDate fecha);
}
