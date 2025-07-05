package com.forohub.controller;


import com.forohub.security.DatosToken;
import com.forohub.security.TokenService;
import com.forohub.usuario.Usuario;
import com.forohub.usuario.dto.DatosAutenticacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    @PostMapping
    public ResponseEntity login (@RequestBody @Valid DatosAutenticacion datos) {
        var authenticacionToken = new UsernamePasswordAuthenticationToken(datos.username(), datos.password());
        var authentiacion = authenticationManager.authenticate(authenticacionToken);

        var token = tokenService.generateToken((Usuario) authentiacion.getPrincipal());
        return ResponseEntity.ok(new DatosToken(token));

    }



}
