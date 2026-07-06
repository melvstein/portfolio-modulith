package dev.melvstein.spring_portfolio_modulith.auth.internal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditActionEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditEntityTpeEnum;
import dev.melvstein.spring_portfolio_modulith.audit.api.enm.AuditModuleEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.dto.AuthUserUpdateRequest;
import dev.melvstein.spring_portfolio_modulith.auth.api.enm.AuthApiResponseEnum;
import dev.melvstein.spring_portfolio_modulith.auth.api.entity.User;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.AuditLogEvent;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.publisher.UserUpdatedPublisher;
import dev.melvstein.spring_portfolio_modulith.auth.internal.repository.UserRepository;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.publisher.UserRegisteredPublisher;
import dev.melvstein.spring_portfolio_modulith.common.api.exception.ApiException;
import dev.melvstein.spring_portfolio_modulith.auth.api.kafka.event.UserRegisteredEvent;
import dev.melvstein.spring_portfolio_modulith.common.utils.RequestHeaderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegisteredPublisher userRegisteredPublisher;
    private final UserUpdatedPublisher userUpdatedPublisher;
    private final ObjectMapper objectMapper;

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ApiException(AuthApiResponseEnum.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException(AuthApiResponseEnum.EMAIL_ALREADY_EXISTS);
        }

        if (userRepository.existsByContactNumber(String.valueOf(user.getContactNumber()))) {
            throw new ApiException(AuthApiResponseEnum.CONTACT_NUMBER_ALREADY_EXISTS);
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        String ipAddess = RequestHeaderUtils.getIpAddress();
        String userAgent = RequestHeaderUtils.getUserAgent();

        AuditLogEvent auditLog = AuditLogEvent.builder()
                .module(AuditModuleEnum.AUTH)
                .action(AuditActionEnum.CREATE)
                .entityType(AuditEntityTpeEnum.USER)
                .entityId(savedUser.getId())
                .actorId(null) // Set the actorId if available
                .description("User registered with username: " + savedUser.getUsername())
                .ipAddress(ipAddess)
                .userAgent(userAgent)
                .build();

        userRegisteredPublisher.publish(
                UserRegisteredEvent.builder()
                        .authUserId(savedUser.getId())
                        .auditLog(auditLog)
                        .build()
        );

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(AuthApiResponseEnum.USER_NOT_FOUND));
    }

    public List<User> getAllUsersByIds(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    @Transactional
    public User updateUserById(UUID userId, AuthUserUpdateRequest request) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(AuthApiResponseEnum.USER_NOT_FOUND));

        JsonNode before = objectMapper.valueToTree(existingUser);

        if (request.role() != null && !request.role().equals(existingUser.getRole())) {
            log.info("[updateUserById] updating user role for userId: {} from {} to {}", userId, existingUser.getRole(), request.role());

            existingUser.setRole(request.role());
        }

        if (request.username() != null
                && !request.username().isBlank()
                && !request.username().equals(existingUser.getUsername())
        ) {
            if (userRepository.existsByUsername(request.username())) {
                throw new ApiException(AuthApiResponseEnum.USERNAME_ALREADY_EXISTS);
            }

            log.info("[updateUserById] updating username for userId: {} from {} to {}", userId, existingUser.getUsername(), request.username());

            existingUser.setUsername(request.username());
        }

        if (request.password() != null && !request.password().isBlank()
        ) {
            if (passwordEncoder.matches(request.password(), existingUser.getPassword())) {
                throw new ApiException(AuthApiResponseEnum.FAILED, "New password matches old password");
            }

            log.info("[updateUserById] updating password for userId: {}", userId);

            String newPassword = passwordEncoder.encode(request.password());
            existingUser.setPassword(newPassword);
        }

        if (request.email() != null
                && !request.email().isBlank()
                && !request.email().equals(existingUser.getEmail())
        ) {
            if (userRepository.existsByEmail(request.email())) {
                throw new ApiException(AuthApiResponseEnum.EMAIL_ALREADY_EXISTS);
            }

            log.info("[updateUserById] updating email for userId: {}", userId);

            existingUser.setEmail(request.email());
        }

        if (request.contactNumber() != null
                && !request.contactNumber().isBlank()
                && !request.contactNumber().equals(existingUser.getContactNumber())
        ) {
            if (userRepository.existsByContactNumber(request.contactNumber())) {
                throw new ApiException(AuthApiResponseEnum.CONTACT_NUMBER_ALREADY_EXISTS);
            }

            log.info("[updateUserById] updating contact number for userId: {}", userId);

            existingUser.setContactNumber(request.contactNumber());
        }

        if (request.status() != null && !request.status().equals(existingUser.getStatus())) {
            log.info("[updateUserById] updating status for userId: {}", userId);

            existingUser.setStatus(request.status());
        }

        if (request.emailVerified() != null && !request.emailVerified().equals(existingUser.isEmailVerified())) {
            log.info("[updateUserById] updating email for userId: {}", userId);

            existingUser.setEmailVerified(request.emailVerified());
        }

        User savedUser = userRepository.save(existingUser);

        String ipAddess = RequestHeaderUtils.getIpAddress();
        String userAgent = RequestHeaderUtils.getUserAgent();
        JsonNode after = objectMapper.valueToTree(savedUser);

        AuditLogEvent auditLog = AuditLogEvent.builder()
                .module(AuditModuleEnum.AUTH)
                .action(AuditActionEnum.UPDATE)
                .entityType(AuditEntityTpeEnum.USER)
                .entityId(savedUser.getId())
                .actorId(null) // Set the actorId if available
                .description("User updated with username: " + savedUser.getUsername())
                .ipAddress(ipAddess)
                .userAgent(userAgent)
                .before(before)
                .after(after)
                .build();

        userUpdatedPublisher.publish(auditLog);

        return savedUser;
    }
}
