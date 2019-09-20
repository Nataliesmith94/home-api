package com.application.homeapi.account;

import com.application.homeapi.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void createNewAccount(Account account) {
        accountRepository.save(account);
    }

    public Account findAccountByEmail(String email) {
       return accountRepository.findByEmail(email);
    }

    public Account findAccountByUserId(String userId) {
        return accountRepository.findByUserId(userId);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
