package dev.melvstein.spring_portfolio_modulith.auth.dto;

import dev.melvstein.spring_portfolio_modulith.auth.enm.AuthRoleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.enm.AuthStatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AuthRegisterRequestDto(

        @NotNull(message = "role is required")
        AuthRoleEnum role,

        @NotBlank(message = "username is required")
        String username,

        @NotBlank(message = "password is required")
        String password,

        @NotBlank(message = "confirmPassword is required")
        String confirmPassword,

        @Email(message = "Invalid email address")
        String email,

        @Pattern(
                regexp = "^[0-9]{11}$",
                message = "Contact number must contain 11 digits"
        )
        String contactNumber,

        @NotNull(message = "status is required")
        AuthStatusEnum status
) {
}
