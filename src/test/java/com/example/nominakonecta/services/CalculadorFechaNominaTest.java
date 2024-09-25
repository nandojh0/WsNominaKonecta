/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.services;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class CalculadorFechaNominaTest {

    @Mock
    private ValidadorDiaHabilColombia validadorDiaHabilColombia;

    @InjectMocks
    private CalculadorFechaNomina calculadorFechaNomina;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEsAntesDel15() {
        LocalDate fechaIngresada = LocalDate.of(2024, 2, 5); // Antes del 15
        LocalDate fechaEsperada = LocalDate.of(2024, 2, 15); // Pago en el 15

        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEs15YEsHabil() {
        LocalDate fechaIngresada = LocalDate.of(2024, 7, 15); // Es 15
        LocalDate fechaEsperada = LocalDate.of(2024, 7, 15); // Pago en el 15

        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEs30YEsHabil() {
        LocalDate fechaIngresada = LocalDate.of(2024, 2, 29); // Es 30
        LocalDate fechaEsperada = LocalDate.of(2024, 2, 29); // Pago en el 30

        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEs30YNoEsHabil() {
        LocalDate fechaIngresada = LocalDate.of(2024, 3, 30); // Es 30, pero no es hábil
        LocalDate fechaEsperada = LocalDate.of(2024, 3, 27); // Pago el 27, que es hábil

        when(validadorDiaHabilColombia.esDiaHabil(LocalDate.of(2024, 3, 28))).thenReturn(false);
        when(validadorDiaHabilColombia.esDiaHabil(LocalDate.of(2024, 3, 29))).thenReturn(false);
        when(validadorDiaHabilColombia.esDiaHabil(LocalDate.of(2024, 3, 30))).thenReturn(false);
  
        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEs15YNoEsHabil() {
        LocalDate fechaIngresada = LocalDate.of(2024, 4, 15); // Es 15, pero no es hábil
        LocalDate fechaEsperada = LocalDate.of(2024, 4, 12); // Pago el 12, que es hábil

        when(validadorDiaHabilColombia.esDiaHabil(LocalDate.of(2024, 4, 15))).thenReturn(false);
        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }

    @Test
    public void testObtenerFechaPago_CuandoFechaEsAntesDel15Y15NoEsHabil() {
        LocalDate fechaIngresada = LocalDate.of(2024, 3, 10); // Antes del 15
        LocalDate fechaEsperada = LocalDate.of(2024, 3, 14); // Pago el 15 no es hábil, por lo que buscamos el 14

        when(validadorDiaHabilColombia.esDiaHabil(LocalDate.of(2024, 3, 15))).thenReturn(false);
        when(validadorDiaHabilColombia.esDiaHabil(fechaEsperada)).thenReturn(true);

        LocalDate resultado = calculadorFechaNomina.obtenerFechaPago(fechaIngresada);
        assertEquals(fechaEsperada, resultado);
    }
}