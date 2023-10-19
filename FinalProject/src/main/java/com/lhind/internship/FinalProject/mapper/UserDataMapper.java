package com.lhind.internship.FinalProject.mapper;

import com.lhind.internship.FinalProject.model.dto.PasswordDto;
import com.lhind.internship.FinalProject.model.dto.UserDto;
import com.lhind.internship.FinalProject.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserDataMapper extends AbstractMapper<User, PasswordDto> {
    @Override
    public User toEntity(PasswordDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }

    @Override
    public PasswordDto toDto(User entity) {
        if (entity == null) {
            return null;
        }

        PasswordDto userDto = new PasswordDto();
        userDto.setId(entity.getId());
        userDto.setFirstName(entity.getFirstName());
        userDto.setMiddleName(entity.getMiddleName());
        userDto.setLastName(entity.getLastName());
        userDto.setEmail(entity.getEmail());
        userDto.setPhoneNumber(entity.getPhoneNumber());
        userDto.setAddress(entity.getAddress());
        userDto.setPassword(entity.getPassword());
        userDto.setRole(entity.getRole());

        return userDto;
    }
    public List<PasswordDto> toDtoList(List<User> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
