package dev.melvstein.spring_portfolio_modulith.auth.mapper;

import dev.melvstein.spring_portfolio_modulith.auth.dto.AuthRegisterRequestDto;
import dev.melvstein.spring_portfolio_modulith.auth.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.entity.User;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .username(user.getUsername())
                .email(user.getEmail())
                .contactNumber(user.getContactNumber())
                .status(user.getStatus())
                .emailVerified(user.isEmailVerified())
                .createdAt(Date.from(user.getCreatedAt()))
                .updatedAt(Date.from(user.getUpdatedAt()))
                .build();
    }

    public User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .id(dto.id())
                .role(dto.role())
                .username(dto.username())
                .email(dto.email())
                .contactNumber(dto.contactNumber())
                .status(dto.status())
                .emailVerified(dto.emailVerified())
                .createdAt(dto.createdAt().toInstant())
                .updatedAt(dto.updatedAt().toInstant())
                .build();
    }

    public User toEntity(AuthRegisterRequestDto request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .role(request.role())
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .contactNumber(request.contactNumber())
                .status(request.status())
                .emailVerified(Boolean.TRUE.equals(request.emailVerified()))
                .build();
    }
}
