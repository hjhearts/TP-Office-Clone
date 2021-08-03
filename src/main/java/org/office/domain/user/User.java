package org.office.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.office.config.domain.BaseTimeEntity;
import org.office.domain.user.dto.request.UserUpdateRequestDto;
import org.office.domain.user.enums.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, unique = true)
    private String userCode;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String korName;

    @Column
    private String engName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserTeam userTeam;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Company company;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    private String businessAgentId;

    @Column
    private LocalDateTime birthday;

    @Column
    private String companyPhoneNumber;

    @Column
    private String cellPhoneNumber;

    @Column
    private String faxNumber;

    @Column
    private String homePhoneNumber;

    @Column
    private String workTime;

    @Column
    private String academicBackground;

    @Column
    private String majorCareer;

    @Column
    private String familyDetail;

    @Column
    private String hobby;

    @Column
    private String majorAchievement;

    @Column(nullable = false)
    private LocalDateTime lastLoginTime;

    @Builder
    public User(String id, String userCode, String password, String korName, UserTeam userTeam, Company company,
                Role role, UserLevel userLevel, UserStatus status, LocalDateTime lastLoginTime) {
        this.id = id;
        this.userCode = userCode;
        this.password = password;
        this.korName = korName;
        this.userTeam = userTeam;
        this.company = company;
        this.role = role;
        this.userLevel = userLevel;
        this.status = status;
        this.lastLoginTime = lastLoginTime;
    }

    public void loggedIn(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 변경될 정보를 받아 객체를 수정함
     * @see UserUpdateRequestDto
     * @param requestDto 변경될 정보
     * @return 변경된 유저
     */
    public User update(UserUpdateRequestDto requestDto) {
        this.korName = requestDto.getKorName();
        this.engName = requestDto.getEngName();
        this.role = requestDto.getRole();
        this.userTeam = requestDto.getUserTeam();
        this.userLevel = requestDto.getUserLevel();
        this.company = requestDto.getCompany();
        this.businessAgentId = requestDto.getBusinessAgentId();
        this.birthday = requestDto.getBirthday();
        this.companyPhoneNumber = requestDto.getCompanyPhoneNumber();
        this.cellPhoneNumber = requestDto.getCellPhoneNumber();
        this.faxNumber = requestDto.getFaxNumber();
        this.homePhoneNumber = requestDto.getHomePhoneNumber();
        this.workTime = requestDto.getWorkTime();
        this.academicBackground = requestDto.getAcademicBackground();
        this.majorCareer = requestDto.getMajorCareer();
        this.familyDetail = requestDto.getFamilyDetail();
        this.hobby = requestDto.getHobby();
        this.majorAchievement = requestDto.getMajorAchievement();
        return this;
    }
}
