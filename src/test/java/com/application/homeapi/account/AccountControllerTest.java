package com.application.homeapi.account;

import com.application.homeapi.domain.Account;
import com.application.homeapi.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {

    @MockBean
    AccountService accountService;

    @Autowired
    MockMvc mockMvc;

    public Account account1, account2;

    @Before
    public void setUp() throws Exception {
        User user1 = new User("Natalie", "Smith");
        account1 = new Account("uuid1", "natalie@msm.com", "pass1234", user1 );

        User user2 = new User("kayne", "kinnear");
        account2 = new Account("uuid2", "kayne@msm.com", "pass12345", user2 );
    }

    @Test
    public void shouldAddAccount() throws Exception {
        mockMvc.perform(post("/api/account/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(" {\n" +
                        "        \"email\": \"josh@msm.com\",\n" +
                        "        \"password\": \"123456\",\n" +
                        "        \"user\" : {\n" +
                        "        \t\"firstName\": \"Josh\",\n" +
                        "        \t\"surname\": \"Powell\"\n" +
                        "        }\n" +
                        "}" ))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllAccountsStored() throws Exception {
        when(accountService.findAll()).thenReturn(Arrays.asList(account1, account2));

        mockMvc.perform(get("/api/account/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].email").value("natalie@msm.com"))
                .andExpect(jsonPath("$[0].user.firstName").value("Natalie"))
                .andExpect(jsonPath("$[1].email").value("kayne@msm.com"))
                .andExpect(jsonPath("$[1].user.firstName").value("kayne"));

    }

    @Test
    public void shouldFindAccountByEmail() throws Exception {
        String email = "natalie@msm.com";
        when(accountService.findAccountByEmail(email)).thenReturn(account1);

        mockMvc.perform(get("/api/account/findEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.firstName").value("Natalie"))
                .andExpect(jsonPath("$.user.surname").value("Smith"));
    }

    @Test
    public void shouldFindAccountById() throws Exception {
        String userId = "uuid2";
        when(accountService.findAccountByUserId(userId)).thenReturn(account2);

        mockMvc.perform(get("/api/account/findId/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("kayne@msm.com"))
                .andExpect(jsonPath("$.user.firstName").value("kayne"))
                .andExpect(jsonPath("$.user.surname").value("kinnear"));
    }
}