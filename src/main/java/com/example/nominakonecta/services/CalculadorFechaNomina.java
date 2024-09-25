/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import java.time.LocalDate;
import java.time.Month;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author nando
 */
@AllArgsConstructor
@Service
public class CalculadorFechaNomina {

    private final ValidadorDiaHabilColombia validadorDiaHabilColombia;

    public LocalDate obtenerFechaPago(LocalDate fechaIngresada) {
        LocalDate dia15 = LocalDate.of(fechaIngresada.getYear(), fechaIngresada.getMonth(), 15);
        LocalDate dia30;

        // Verificar si es febrero y manejar los días
        if (fechaIngresada.getMonth() == Month.FEBRUARY) {
            // Febrero solo tiene 28 días (o 29 en bisiestos)
            dia30 = LocalDate.of(fechaIngresada.getYear(), Month.FEBRUARY, 28);
            if (fechaIngresada.getYear() % 4 == 0 && (fechaIngresada.getYear() % 100 != 0 || fechaIngresada.getYear() % 400 == 0)) {
                dia30 = LocalDate.of(fechaIngresada.getYear(), Month.FEBRUARY, 29); // Años bisiestos
            }
        } else {
            dia30 = LocalDate.of(fechaIngresada.getYear(), fechaIngresada.getMonth(), 30);
        }
        
        LocalDate proximaFechaPago;

        // Verificar si la fecha ingresada es el día 15 o antes
        if (fechaIngresada.isEqual(dia15) || fechaIngresada.isBefore(dia15)) {
            proximaFechaPago = dia15;
        } else {
            proximaFechaPago = dia30;
        }

        // Si la fecha de pago no es hábil, buscar el día hábil anterior
        while (!validadorDiaHabilColombia.esDiaHabil(proximaFechaPago)) {
            proximaFechaPago = proximaFechaPago.minusDays(1);
        }

        return proximaFechaPago;
    }

}
