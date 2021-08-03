package org.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.office.domain.user.User;
import org.office.domain.user.UserRepository;
import org.office.domain.user.dto.request.UserSaveRequestDto;
import org.office.domain.user.dto.request.UserUpdateRequestDto;
import org.office.domain.user.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDomainTest {

    @LocalServerPort
    private int port;

    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity()).build();

        String url = "http://localhost:" + port + "/api/v1/user";

        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .id("gkska1029@naver.com")
                .password("12345")
                .korName("한")
                .userTeam(UserTeam.SYSTEM_TECHNOLOGY_MANAGEMENT)
                .company(Company.ASIANA_IDT)
                .userLevel(UserLevel.AB_SENIOR_EXECUTIVE_VICE_PRESIDENT)
                .build();

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto))).andExpect(status().isOk());
    }

    @After
    public void after_job() {
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void user_save_test() {
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);
        assertThat(user.getStatus()).isEqualTo(UserStatus.WAITING);
        assertThat(user.getCompany()).isEqualTo(Company.ASIANA_IDT);
        assertThat(user.getId()).isEqualTo("gkska1029@naver.com");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void user_update_test() throws Exception {
        List<User> userList = userRepository.findAll();
        User user = userList.get(0);
        assertThat(user.getCompany()).isEqualTo(Company.ASIANA_IDT);

        String userCode = user.getUserCode();
        UserUpdateRequestDto updateRequestDto = UserUpdateRequestDto.builder()
                .korName("한")
                .company(Company.ASIANA_AIRLINE)
                .userTeam(UserTeam.SYSTEM_TECHNOLOGY_MANAGEMENT)
                .role(Role.USER)
                .userLevel(UserLevel.AB_SENIOR_EXECUTIVE_VICE_PRESIDENT)
                .build();

        String url = "http://localhost:" + port + "/api/v1/user/" + userCode;
        mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(updateRequestDto))).andExpect(status().isOk());

        userList = userRepository.findAll();
        user = userList.get(0);
        System.out.println("user_update_test() : user.getCompany() : " + user.getCompany().getKorCompanyName());
        assertThat(user.getCompany()).isEqualTo(Company.ASIANA_AIRLINE);
    }

    @Test
    @WithMockUser(roles = "DEVELOPER")
    public void user_delete_test() throws Exception {
        List<User> userList = userRepository.findAll();
        System.out.println("user_delete_test() : userList.size() = " + userList.size());
        assertThat(userList.size()).isEqualTo(1);

        User user = userList.get(0);
        String userCode = user.getUserCode();
        String url = "http://localhost:" + port + "/api/v1/user/" + userCode;

        mvc.perform(delete(url)).andExpect(status().isOk());

        userList = userRepository.findAll();
        System.out.println("user_delete_test() : userList.size() = " + userList.size());
        assertThat(userList.size()).isEqualTo(0);
    }
}
