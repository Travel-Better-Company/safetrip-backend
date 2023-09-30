package com.safetripbackend.user.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tuempresa.tuaplicacion.entity.User;
import com.tuempresa.tuaplicacion.servicios.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService usuarioService;

    @Autowired
    public UserController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public Usuario registrarUsuario(@RequestBody User usuario) {
        return usuarioService.registrarUsuario(usuario);
    }
}