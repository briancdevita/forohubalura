package com.forohub.controller;


import com.forohub.topico.Topico;
import com.forohub.topico.dto.DatosActualizacionTopico;
import com.forohub.topico.dto.DatosDetalleTopico;
import com.forohub.topico.dto.DatosPostTopico;
import com.forohub.topico.repository.TopicoRepository;
import com.forohub.usuario.Usuario;
import com.forohub.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoController {



    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleTopico> post(@RequestBody @Valid  DatosPostTopico datos) {
        System.out.println("Datos del topico: " + datos);

        // 1. Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // El nombre de usuario del principal

        // 2. Buscar el usuario en la base de datos (asegúrate de que exista)
        Usuario autor = (Usuario) usuarioRepository.findByUsername(username);

        // 3. Crear el Topico con el autor obtenido
        var nuevoTopico = new Topico(datos.titulo(), datos.mensaje(), LocalDateTime.now(), true, datos.curso(), autor);

        topicoRepository.save(nuevoTopico);

        URI uri = UriComponentsBuilder.fromPath("/topicos/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(nuevoTopico));
    }


    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> obtenerTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        Page<Topico> topicosPage = topicoRepository.findAll(paginacion);
        List<DatosDetalleTopico> datosDetalleTopicos = topicosPage.stream()
                .map(DatosDetalleTopico::new)
                .collect(Collectors.toList());
        Page<DatosDetalleTopico> datosDetalleTopicosPage = topicosPage.map(DatosDetalleTopico::new);

        return ResponseEntity.ok(datosDetalleTopicosPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id: " + id));

        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);
        return ResponseEntity.ok(datosDetalleTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosActualizacionTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosPostTopico datos) {
        System.out.println("Datos de actualización del topico: " + datos);
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id: " + id));


        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setCurso(datos.curso());

        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosActualizacionTopico(topico));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id: " + id));

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }


}
