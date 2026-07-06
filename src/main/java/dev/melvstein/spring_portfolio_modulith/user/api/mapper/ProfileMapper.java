package dev.melvstein.spring_portfolio_modulith.user.api.mapper;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.user.api.dto.UserProfileDto;
import dev.melvstein.spring_portfolio_modulith.user.api.entity.Profile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProfileMapper {

    public static List<UserProfileDto> toUserProfileDtos(List<UserDto> userDtos, List<Profile> profiles) {

        if (userDtos == null || profiles == null) {
            return List.of();
        }

        Map<UUID, Profile> profileMap = profiles.stream()
                .collect(Collectors.toMap(
                        Profile::getAuthUserId,
                        Function.identity()
                ));

        return userDtos.stream()
                .map(userDto -> {
                    Profile profile = profileMap.get(userDto.id());

                    if (profile == null) {
                        return null;
                    }

                    Date profileUpdatedAt = Date.from(profile.getUpdatedAt());
                    Date updatedAt = userDto.updatedAt().after(profileUpdatedAt)
                            ? userDto.updatedAt()
                            : profileUpdatedAt;

                    return UserProfileDto.builder()
                            .id(userDto.id())
                            .role(userDto.role())
                            .firstName(profile.getFirstName())
                            .middleName(profile.getMiddleName())
                            .lastName(profile.getLastName())
                            .headline(profile.getHeadline())
                            .bio(profile.getBio())
                            .avatarUrl(profile.getAvatarUrl())
                            .website(profile.getWebsite())
                            .githubUrl(profile.getGithubUrl())
                            .linkedinUrl(profile.getLinkedinUrl())
                            .extraInfo(profile.getExtraInfo())
                            .username(userDto.username())
                            .email(userDto.email())
                            .contactNumber(userDto.contactNumber())
                            .status(userDto.status())
                            .emailVerified(userDto.emailVerified())
                            .createdAt(userDto.createdAt())
                            .updatedAt(updatedAt)
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
