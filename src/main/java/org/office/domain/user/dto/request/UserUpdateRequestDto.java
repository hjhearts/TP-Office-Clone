package org.office.domain.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.office.domain.user.enums.Company;
import org.office.domain.user.enums.Role;
import org.office.domain.user.enums.UserLevel;
import org.office.domain.user.enums.UserTeam;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String korName;
    private String engName;
    private Role role;
    private UserTeam userTeam;
    private UserLevel userLevel;
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

    /**
     * 업데이트를 관장하는 DTO
     * 두가지 전략
     * 1. service 단에서 null-check 를 통해 null 값을 기존 데이터의 값으로 대체
     * 2. readonly 속성 또는 변경 가능한 경우를 고려하여 모든 데이터를 json 으로 전달
     * @param korName NOTNULL
     * @param engName O
     * @param role NOTNULL
     * @param userTeam NOTNULL
     * @param userLevel NOTNULL
     * @param company NOTNULL
     * @param businessAgentId O
     * @param birthday P
     * @param companyPhoneNumber O
     * @param cellPhoneNumber O
     * @param faxNumber O
     * @param homePhoneNumber O
     * @param workTime O
     * @param academicBackground O
     * @param majorCareer O
     * @param familyDetail O
     * @param hobby O
     * @param majorAchievement O
     */
    @Builder
    public UserUpdateRequestDto(String korName, String engName, Role role, UserTeam userTeam, UserLevel userLevel,
                                Company company, String businessAgentId, LocalDateTime birthday,
                                String companyPhoneNumber, String cellPhoneNumber, String faxNumber,
                                String homePhoneNumber, String workTime, String academicBackground,
                                String majorCareer, String familyDetail, String hobby, String majorAchievement) {
        this.korName = korName;
        this.engName = engName;
        this.role = role;
        this.userTeam = userTeam;
        this.userLevel = userLevel;
        this.company = company;
        this.businessAgentId = businessAgentId;
        this.birthday = birthday;
        this.companyPhoneNumber = companyPhoneNumber;
        this.cellPhoneNumber = cellPhoneNumber;
        this.faxNumber = faxNumber;
        this.homePhoneNumber = homePhoneNumber;
        this.workTime = workTime;
        this.academicBackground = academicBackground;
        this.majorCareer = majorCareer;
        this.familyDetail = familyDetail;
        this.hobby = hobby;
        this.majorAchievement = majorAchievement;
    }
}
