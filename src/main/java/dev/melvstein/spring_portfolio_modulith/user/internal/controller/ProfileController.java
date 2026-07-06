package dev.melvstein.spring_portfolio_modulith.user.internal.controller;

import dev.melvstein.spring_portfolio_modulith.common.api.vo.ApiResponseVo;
import dev.melvstein.spring_portfolio_modulith.user.api.dto.UserProfileDto;
import dev.melvstein.spring_portfolio_modulith.user.internal.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseVo<List<UserProfileDto>>> getAllProfiles() {
        List<UserProfileDto> profiles = profileService.getAllProfiles();

        return ResponseEntity.ok(ApiResponseVo.success(profiles));
    }
}
