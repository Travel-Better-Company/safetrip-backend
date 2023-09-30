package com.pe.safetripbackend.subscription.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tuempresa.tuaplicacion.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}