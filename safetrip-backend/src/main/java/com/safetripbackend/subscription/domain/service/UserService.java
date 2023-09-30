package com.safetripbackend.user.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tuempresa.tuaplicacion.entidades.Usuario;
import com.tuempresa.tuaplicacion.repositorios.UsuarioRepository;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registrarUsuario(User usuario) {

        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }

    public User encontrarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}