package org.office.domain.user.dto.secure;

import lombok.Getter;
import org.office.domain.user.User;
import org.office.domain.user.enums.*;

import java.time.LocalDateTime;

@Getter
public class SessionUser {
    private String id;
    private String korName;
    private String engName;
    private Role role;
    private UserTeam userTeam;
    private UserLevel userLevel;
    private UserStatus status;
    private Company company;
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

    public SessionUser(User entity) {
        this.id = entity.getId();
        this.korName = entity.getKorName();
        this.engName = entity.getEngName();
        this.role = entity.getRole();
        this.userTeam = entity.getUserTeam();
        this.userLevel = entity.getUserLevel();
        this.status = entity.getStatus();
        this.company = entity.getCompany();
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
