package com.forohub.topico.dto;


import com.forohub.topico.Topico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record DatosDetalleTopico(
    @NotNull Long id,
    @NotBlank String  titulo,
    @NotBlank String mensaje,

    @Future LocalDateTime fecha

) {

  public DatosDetalleTopico(Topico nuevoTopico) {
    this(nuevoTopico.getId(), nuevoTopico.getTitulo(), nuevoTopico.getMensaje(), nuevoTopico.getFecha());
  }


}
