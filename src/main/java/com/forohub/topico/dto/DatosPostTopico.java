package com.forohub.topico.dto;


import jakarta.validation.constraints.NotBlank;


public record DatosPostTopico(
       @NotBlank String titulo,
       @NotBlank String mensaje,
       @NotBlank String curso
) {

}
