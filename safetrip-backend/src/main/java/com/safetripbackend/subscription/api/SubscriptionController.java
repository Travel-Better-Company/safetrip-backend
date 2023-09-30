package com.pe.safetripbackend.subscription.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tuempresa.tuaplicacion.entidades.Usuario;
import com.tuempresa.tuaplicacion.servicios.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    private final UsuarioService usuarioService;

    @Autowired
    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public Usuario registrarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}