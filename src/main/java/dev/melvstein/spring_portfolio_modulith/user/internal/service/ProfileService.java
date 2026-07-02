package dev.melvstein.spring_portfolio_modulith.user.internal.service;

import dev.melvstein.spring_portfolio_modulith.common.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.user.entity.Profile;
import dev.melvstein.spring_portfolio_modulith.user.internal.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Transactional
    public void createProfile(UserRegisteredEvent event) {
        Profile profile = Profile.builder()
                .authUserId(event.authUserId())
                .build();

        profileRepository.save(profile);
    }
}
