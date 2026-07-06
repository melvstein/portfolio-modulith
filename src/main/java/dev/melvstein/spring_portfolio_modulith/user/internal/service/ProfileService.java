package dev.melvstein.spring_portfolio_modulith.user.internal.service;

import dev.melvstein.spring_portfolio_modulith.auth.api.dto.UserDto;
import dev.melvstein.spring_portfolio_modulith.auth.api.facade.AuthFacade;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.user.entity.Profile;
import dev.melvstein.spring_portfolio_modulith.user.internal.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final AuthFacade authFacade;

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public List<UserDto> getAllProfiles() {
        return authFacade.getAllUsers();
    }

    @Transactional
    public void createProfile(UserRegisteredEvent event) {
        Profile profile = Profile.builder()
                .authUserId(event.authUserId())
                .build();

        profileRepository.save(profile);
    }
}
