package com.safetripbackend.user.mappers;

import ccom.pe.safetripbackend.subscription.domain.entity.User;
import ccom.pe.safetripbackend.subscription.resource.UserResource;
import com.pe.safetripbackend.subscription.resource.UserResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "numeroTarjeta", target = "numeroTarjeta") 
    @Mapping(source = "fechaExpiracion", target = "fechaExpiracion")
    @Mapping(source = "cvv", target = "cvv")
    UserDTO usuarioToUsuarioDTO(User usuario);
}
