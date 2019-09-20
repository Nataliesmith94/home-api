package com.application.homeapi.account;

import com.application.homeapi.domain.Account;
import com.application.homeapi.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
@AutoConfigureTestEntityManager
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private Account account1, account2;

    @Before
    public void setUp() {
        account1 = createAccount("uuid01", "natalie@msm.com", "pass1234", "natalie", "smith");
        account2 = createAccount("uuid02", "adam@msm.com", "pass1234", "adam", "smith");
    }

    @Test
    public void findByEmail() {
        Account account = accountRepository.findByEmail("natalie@msm.com");
        assertEquals(account, account1);
    }

    @Test
    public void findByUserId() {
        Account account = accountRepository.findByUserId("uuid02");
        assertEquals(account, account2);
    }


    private Account createAccount(String userId, String email, String password, String firstName, String surname) {
        User user = createUser(firstName, surname);
        Account account = new Account(userId, email, password, user);
        return accountRepository.save(account);
    }

    private User createUser(String firstName, String surname) {
        User user = new User(firstName, surname);
        return user;
    }
}