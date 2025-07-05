package com.forohub.topico.dto;

import com.forohub.usuario.Usuario;


public record DatosAutor(
        Long id,
        String username

)
{
    public DatosAutor( Usuario autor) {
        this(autor.getId(), autor.getUsername());
    }
}
