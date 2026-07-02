package dev.melvstein.spring_portfolio_modulith.auth.dto;

import dev.melvstein.spring_portfolio_modulith.auth.enm.AuthRoleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.enm.AuthStatusEnum;
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
        Date createdAt,
        Date updatedAt
) {
}
