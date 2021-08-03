package org.office.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.office.domain.user.User;
import org.office.domain.user.enums.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String id;
    private String userCode;
    private String korName;
    private String engName;
    private UserTeam userTeam;
    private Company company;
    private Role role;
    private UserLevel userLevel;
    private UserStatus status;
    private String businessAgentId;
    private LocalDateTime birthday;
    private String companyPhoneNumber;
    private String cellPhoneNumber;
    private String faxNumber;
    private String homePhoneNumber;
    private String workTime;
    private String academicBackground;
    private String majorCareer;
    private String familyDetail;
    private String hobby;
    private String majorAchievement;
    private LocalDateTime lastLoginTime;

    public UserInfoResponseDto(User entity) {
        this.id = entity.getId();
        this.userCode = entity.getUserCode();
        this.korName = entity.getKorName();
        this.engName = entity.getEngName();
        this.userTeam = entity.getUserTeam();
        this.company = entity.getCompany();
        this.role = entity.getRole();
        this.userLevel = entity.getUserLevel();
        this.status = entity.getStatus();
        this.businessAgentId = entity.getBusinessAgentId();
        this.birthday = entity.getBirthday();
        this.companyPhoneNumber = entity.getCompanyPhoneNumber();
        this.cellPhoneNumber = entity.getCellPhoneNumber();
        this.faxNumber = entity.getFaxNumber();
        this.homePhoneNumber = entity.getHomePhoneNumber();
        this.workTime = entity.getWorkTime();
        this.academicBackground = entity.getAcademicBackground();
        this.majorCareer = entity.getMajorCareer();
        this.familyDetail = entity.getFamilyDetail();
        this.hobby = entity.getHobby();
        this.majorAchievement = entity.getMajorAchievement();
        this.lastLoginTime = entity.getLastLoginTime();
    }
}
