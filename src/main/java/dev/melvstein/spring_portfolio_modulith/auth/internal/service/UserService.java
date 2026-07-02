package dev.melvstein.spring_portfolio_modulith.auth.internal.service;

import dev.melvstein.spring_portfolio_modulith.auth.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.internal.repository.UserRepository;
import dev.melvstein.spring_portfolio_modulith.common.enm.ApiResponse;
import dev.melvstein.spring_portfolio_modulith.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ApiResponse.USER_NOT_FOUND));
    }
}
