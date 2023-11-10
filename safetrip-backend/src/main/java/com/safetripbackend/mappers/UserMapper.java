package com.safetripbackend.mappers;
import com.safetripbackend.dto.UserRequestDto;
import com.safetripbackend.dto.UserResponseDto;
import com.safetripbackend.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Users resourceToEntity(UserRequestDto userResource) {
        return modelMapper.map(userResource, Users.class);
    }

    public UserRequestDto entityToResource(Users customer) {
        return modelMapper.map(customer, UserRequestDto.class);
    }

    public UserResponseDto entityToResponseResource(Users customer) {
        return modelMapper.map(customer, UserResponseDto.class);
    }

    public List<Users> resourceListToEntityList(List<UserRequestDto> customerResources) {
        return customerResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<UserRequestDto> entityListToResourceList(List<Users> customers) {
        return customers
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<UserResponseDto> entityListToResponseResourceList(List<Users> customers) {
        return customers
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }

    public UserResponseDto mapToDTO(Users user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}
