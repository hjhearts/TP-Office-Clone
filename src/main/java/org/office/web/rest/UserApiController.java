package org.office.web.rest;

import lombok.RequiredArgsConstructor;
import org.office.domain.user.dto.request.UserSaveRequestDto;
import org.office.domain.user.dto.request.UserUpdateRequestDto;
import org.office.service.user.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_MANAGER', 'ROLE_DEVELOPER')")
    @PostMapping("/api/v1/user")
    public String userSignupControl(@RequestBody UserSaveRequestDto requestDto) {
        return userService.userSaveService(requestDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER_MANAGER', 'ROLE_DEVELOPER')")
    @PutMapping("/api/v1/user/{code}")
    public String userUpdateControl(@RequestBody UserUpdateRequestDto requestDto,
                                @PathVariable("code") String userCode) {
        return userService.userUpdateService(requestDto, userCode);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    @DeleteMapping("/api/v1/user/{code}")
    public void userDeleteControl(@PathVariable("code") String userCode) {
        userService.userDeleteService(userCode);
    }
}
