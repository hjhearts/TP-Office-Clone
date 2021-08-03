package org.office.service.user;

import lombok.RequiredArgsConstructor;
import org.office.domain.user.User;
import org.office.domain.user.UserRepository;
import org.office.domain.user.dto.request.UserSaveRequestDto;
import org.office.domain.user.dto.request.UserUpdateRequestDto;
import org.office.domain.user.dto.secure.SessionUser;
import org.office.domain.user.enums.UserStatus;
import org.office.domain.user.exceptions.UserNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User 정보를 찾을 수 없습니다."));
        if (user != null && user.getStatus().equals(UserStatus.ACTIVE)) {
            user.loggedIn(LocalDateTime.now());
        }
        assert user != null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getKey()));
        httpSession.setAttribute("user", new SessionUser(user));
        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), authorities);
    }

    @Transactional
    public String userSaveService(UserSaveRequestDto requestDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        List<User> users = userRepository.findAll();
        while(true) {
            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            boolean isValid = true;
            for (User user : users) {
                if (user.getUserCode().equals(generatedString)) {
                    isValid = false;
                    break;
                }
            }
            if(isValid) {
                requestDto.setUserCode(generatedString);
                break;
            }
        }
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public String userUpdateService(UserUpdateRequestDto requestDto, String userCode) {
        User user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new UserNotFoundException("User 정보를 찾을 수 없습니다."));
        userRepository.save(user.update(requestDto));
        return userCode;
    }

    @Transactional
    public void userDeleteService(String userCode) {
        User user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new UserNotFoundException("User 정보를 찾을 수 없습니다."));
        userRepository.delete(user);
    }
}
