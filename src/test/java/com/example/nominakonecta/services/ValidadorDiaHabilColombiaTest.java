/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author nando
 */
public class ValidadorDiaHabilColombiaTest {

    @Mock
    private FestivoService festivoService;

    @InjectMocks
    private ValidadorDiaHabilColombia validador;

    private List<LocalDate> festivos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar la lista de festivos
        festivos = Arrays.asList(
                LocalDate.of(2024, 1, 1), // Año Nuevo
                LocalDate.of(2024, 1, 8), // Día de los Reyes Magos
                LocalDate.of(2024, 3, 20), // Día de San José
                LocalDate.of(2024, 3, 28), // Jueves Santo
                LocalDate.of(2024, 3, 29), // Viernes Santo
                LocalDate.of(2024, 5, 1), // Día del Trabajo
                LocalDate.of(2024, 5, 13), // Ascensión del Señor
                LocalDate.of(2024, 6, 3), // Corpus Christi
                LocalDate.of(2024, 6, 10), // Sagrado Corazón
                LocalDate.of(2024, 7, 20), // Día de la Independencia
                LocalDate.of(2024, 8, 7), // Batalla de Boyacá
                LocalDate.of(2024, 8, 19), // La Asunción de la Virgen
                LocalDate.of(2024, 10, 14), // Día de la Raza
                LocalDate.of(2024, 11, 4), // Todos los Santos
                LocalDate.of(2024, 11, 11), // Independencia de Cartagena
                LocalDate.of(2024, 12, 8), // Día de la Inmaculada Concepción
                LocalDate.of(2024, 12, 25) // Navidad
        );
    }

    @Test
    public void testEsDiaHabil_CuandoEsLunesYNoEsFestivo() {
        LocalDate fecha = LocalDate.of(2024, 9, 30); // Lunes
        when(festivoService.obtenerFestivos()).thenReturn(festivos);

        assertTrue(validador.esDiaHabil(fecha), "El día debe ser hábil.");
    }

    @Test
    public void testEsDiaHabil_CuandoEsSabado() {
        LocalDate fecha = LocalDate.of(2024, 9, 28); // Sábado
        when(festivoService.obtenerFestivos()).thenReturn(Arrays.asList());

        assertFalse(validador.esDiaHabil(fecha), "El día no debe ser hábil, es sábado.");
    }

    @Test
    public void testEsDiaHabil_CuandoEsDomingo() {
        LocalDate fecha = LocalDate.of(2024, 9, 29); // Domingo
        when(festivoService.obtenerFestivos()).thenReturn(festivos);

        assertFalse(validador.esDiaHabil(fecha), "El día no debe ser hábil, es domingo.");
    }

    @Test
    public void testEsDiaHabil_CuandoEsFestivo() {
        LocalDate fecha = LocalDate.of(2024, 10, 14); // Fiesta
        when(festivoService.obtenerFestivos()).thenReturn(festivos);

        assertFalse(validador.esDiaHabil(fecha), "El día no debe ser hábil, es festivo.");
    }

    @Test
    public void testEsDiaHabil_CuandoEsDiaHabil() {
        LocalDate fecha = LocalDate.of(2024, 10, 11); // Día hábil
        when(festivoService.obtenerFestivos()).thenReturn(festivos);

        assertTrue(validador.esDiaHabil(fecha), "El día debe ser hábil.");
    }
}
