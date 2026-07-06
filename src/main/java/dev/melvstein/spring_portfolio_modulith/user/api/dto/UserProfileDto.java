package dev.melvstein.spring_portfolio_modulith.user.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthRoleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthStatusEnum;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record UserProfileDto(

        UUID id,
        AuthRoleEnum role,
        String firstName,
        String middleName,
        String lastName,
        String headline,
        String bio,
        String avatarUrl,
        String website,
        String githubUrl,
        String linkedinUrl,
        JsonNode extraInfo,
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
