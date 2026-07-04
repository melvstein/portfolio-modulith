package dev.melvstein.spring_portfolio_modulith.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthRoleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthStatusEnum;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record UserDto(

        UUID id,
        AuthRoleEnum role,
        String username,
        String email,
        String contactNumber,
        AuthStatusEnum status,
        boolean emailVerified,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date createdAt,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date updatedAt
) {
}
