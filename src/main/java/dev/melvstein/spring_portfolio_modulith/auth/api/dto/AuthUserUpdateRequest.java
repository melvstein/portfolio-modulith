package dev.melvstein.spring_portfolio_modulith.auth.api.dto;

import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthRoleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthStatusEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AuthUserUpdateRequest(

        AuthRoleEnum role,
        String username,
        String password,
        String email,

        @Pattern(
                regexp = "^[0-9]{11}$",
                message = "Contact number must contain 11 digits"
        )
        String contactNumber,

        AuthStatusEnum status,
        Boolean emailVerified
) {
}
