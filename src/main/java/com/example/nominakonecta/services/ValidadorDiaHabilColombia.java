/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author nando
 */

@AllArgsConstructor
@Service
public class ValidadorDiaHabilColombia {
    
    private final FestivoService festivoService;
    
    
    public boolean esDiaHabil(LocalDate fecha) {
        List<LocalDate> festivos = festivoService.obtenerFestivos();

        // Verificar si es sábado o domingo
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return false;
        }

        // Verificar si es un festivo
        return !festivos.contains(fecha);
    }
}
