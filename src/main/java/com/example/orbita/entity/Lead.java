package com.example.orbita.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    @NotBlank(message = "El tipo de proyecto es obligatorio")
    private String tipo;

    @NotBlank(message = "El alcance es obligatorio")
    private String alcance;

    @Size(max = 2000, message = "El mensaje no puede superar los 2000 caracteres")
    private String mensaje;

    @NotBlank(message = "El contacto es obligatorio")
    @Column(length = 500)
    @Size(max = 500, message = "El contacto no puede superar los 500 caracteres")
    private String contacto;

    @Min(value = 1, message = "Los hits deben ser al menos 1")
    private Integer hits;

    @Min(value = 0, message = "El estimado no puede ser negativo")
    private Integer estimado;
}
