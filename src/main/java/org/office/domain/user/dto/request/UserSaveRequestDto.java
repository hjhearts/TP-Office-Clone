package org.office.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.office.domain.user.User;
import org.office.domain.user.enums.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String id;
    private String userCode;
    private String password;
    private String korName;
    private UserTeam userTeam;
    private Company company;
    private UserLevel userLevel;

    /**
     * 모든 파라미터들은 form 내에 필히 포함되어야 함
     * @param id 고유 id 일반적으로 이메일 주소
     * @param password 사용자 패스워드
     * @param korName 이름(한국어)
     * @param userTeam 부서명
     * @param company 회사명
     * @param userLevel 직급
     */
    @Builder
    public UserSaveRequestDto(String id, String password, String korName,
                              UserTeam userTeam, Company company, UserLevel userLevel) {
        this.id = id;
        this.password = password;
        this.korName = korName;
        this.userTeam = userTeam;
        this.company = company;
        this.userLevel = userLevel;
    }

    public User toEntity() {

        return User.builder()
                .id(id)
                .userCode(userCode)
                .password(password)
                .korName(korName)
                .userTeam(userTeam)
                .userLevel(userLevel)
                .company(company)
                .role(Role.USER)
                .status(UserStatus.WAITING)
                .lastLoginTime(LocalDateTime.now())
                .build();
    }
}
