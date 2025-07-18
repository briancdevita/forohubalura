package com.forohub.topico;


import com.forohub.topico.dto.DatosPostTopico;
import com.forohub.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
public class Topico {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String curso;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor")
    private Usuario autor;

    public Topico() {
    }
    public Topico(String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, String curso, Usuario autor) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.curso = curso;
        this.autor = autor;
    }

    public Topico(DatosPostTopico topico) {
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.curso = topico.curso();
    }

    public Topico(@NotBlank String titulo, @NotBlank String mensaje, Usuario usuario, @NotBlank String curso) {
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fecha_creacion) {
        this.fechaCreacion = fecha_creacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public  Usuario getAutor() {
        return autor;
    }
}
