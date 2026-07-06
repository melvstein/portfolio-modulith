package dev.melvstein.spring_portfolio_modulith.user.internal.service;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.facade.AuthFacade;
import dev.melvstein.spring_portfolio_modulith.common.api.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.user.api.dto.UserProfileDto;
import dev.melvstein.spring_portfolio_modulith.user.api.entity.Profile;
import dev.melvstein.spring_portfolio_modulith.user.api.mapper.ProfileMapper;
import dev.melvstein.spring_portfolio_modulith.user.internal.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AuthFacade authFacade;

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public List<UserProfileDto> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();

        if (profiles.isEmpty()) {
            return List.of();
        }

        List<UUID> authUserIds = profiles.stream()
                .map(Profile::getAuthUserId)
                .toList();

        List<UserDto> userDtos = authFacade.getAllUsersByIds(authUserIds);

        return ProfileMapper.toUserProfileDtos(userDtos, profiles);
    }

    @Transactional
    public void createProfile(UserRegisteredEvent event) {
        Profile profile = Profile.builder()
                .authUserId(event.authUserId())
                .build();

        profileRepository.save(profile);
    }
}
