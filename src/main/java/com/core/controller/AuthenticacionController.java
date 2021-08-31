package com.core.controller;

import com.core.domain.Usuario;
import com.core.service.EmailSendService;
import com.core.service.UsuarioService;
import java.io.IOException;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthenticacionController {

    private UsuarioService usuarioService;
    private EmailSendService serviceEmail;

    @PostMapping("/register")
    public ResponseEntity<?> registroUser(@Valid @RequestBody Usuario usuario) throws IOException{
        serviceEmail.send(usuario); 
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.registroUsuario(usuario));
    }
}
