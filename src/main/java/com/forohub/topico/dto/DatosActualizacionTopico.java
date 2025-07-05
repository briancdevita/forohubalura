package com.forohub.topico.dto;

import com.forohub.topico.Topico;

public record DatosActualizacionTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso
) {

    public DatosActualizacionTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso()
        );
    }
}
