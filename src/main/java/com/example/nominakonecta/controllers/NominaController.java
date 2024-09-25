/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.nominakonecta.controllers;

import com.example.nominakonecta.models.FestivoRequest;
import com.example.nominakonecta.models.Persona;
import com.example.nominakonecta.models.Response;
import com.example.nominakonecta.services.CalculadorFechaNomina;
import com.example.nominakonecta.services.PersonaService;
import java.time.LocalDate;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author nando
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/nomina")
public class NominaController {

    private final PersonaService personaService;
    private final CalculadorFechaNomina calculadorFechaNomina;

    @GetMapping("/proxima-fecha-pago/{empleadoId}")
    public ResponseEntity<Response<String>> obtenerProximaFechaPagoParaEmpleado(@PathVariable Long empleadoId) {
        Optional<Persona> persona = personaService.obtenerPersonaPorId(empleadoId);
        if (persona.isPresent()) {
            Persona p = persona.get();
            LocalDate fechaPago = calculadorFechaNomina.obtenerFechaPago(p.getFechaIngreso());
            Response<String> response = Response.<String>builder()
                    .message("SUCCES")
                    .code("000")
                    .data(fechaPago.toString())
                    .build();
            return ResponseEntity.ok().body(response);
        } else {
            Response<String> response = Response.<String>builder()
                    .message("Empleado no encontrado")
                    .code("001")
                    .build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/proxima-fecha-pago")
    public ResponseEntity<Response<String>> obtenerProximaFecha(@RequestBody FestivoRequest request) {
        try {
            LocalDate fechaPago = calculadorFechaNomina.obtenerFechaPago(request.getFecha());
            Response<String> response = Response.<String>builder()
                    .message("SUCCES")
                    .code("000")
                    .data(fechaPago.toString())
                    .build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            Response<String> response = Response.<String>builder()
                    .message("Error en fecha: " + e.getMessage())
                    .code("500")
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
