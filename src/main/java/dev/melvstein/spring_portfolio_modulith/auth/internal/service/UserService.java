package dev.melvstein.spring_portfolio_modulith.auth.internal.service;

import dev.melvstein.spring_portfolio_modulith.auth.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.internal.repository.UserRepository;
import dev.melvstein.spring_portfolio_modulith.auth.kafka.UserRegisteredPublisher;
import dev.melvstein.spring_portfolio_modulith.common.enm.ApiResponse;
import dev.melvstein.spring_portfolio_modulith.common.exception.ApiException;
import dev.melvstein.spring_portfolio_modulith.common.kafka.event.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisteredPublisher userRegisteredPublisher;

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ApiException(ApiResponse.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException(ApiResponse.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByContactNumber(String.valueOf(user.getContactNumber()))) {
            throw new ApiException(ApiResponse.CONTACT_NUMBER_ALREADY_EXISTS);
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        userRegisteredPublisher.publish(
                UserRegisteredEvent.builder()
                        .authUserId(savedUser.getId())
                        .build()
        );

        return savedUser;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ApiResponse.USER_NOT_FOUND));
    }
}
