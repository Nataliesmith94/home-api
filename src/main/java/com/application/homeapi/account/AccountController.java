package com.application.homeapi.account;

import com.application.homeapi.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name = "email") String email) {
        Account account = accountService.findAccountByEmail(email);
        String name = account.toString();
        return "Hello " + name + " this is your account";
    }

    @RequestMapping("/account/findAll")
    public List<Account> findAllAccounts() {
        List<Account> accountList = accountService.findAll();
        Optional.ofNullable(accountList).orElseThrow(RuntimeException::new);
        return accountList;
    }

    @RequestMapping(value = "/account/create", method = RequestMethod.POST)
    public void AddNewRecipe(@RequestBody Account account) {
        accountService.createNewAccount(account);
    }

    @GetMapping("/account/findEmail")
    public Account FindAccountByEmail(@RequestParam(name = "email") String email) {
        Account account = accountService.findAccountByEmail(email);
        System.out.println("FIND BY EMAIL::::");
        System.out.println(account);
        return account;
    }

    @RequestMapping("/account/findId/{userId}")
    public Account FindAccountByUserId(@PathVariable("userId") String userId) {
        Account account = accountService.findAccountByUserId(userId);
        System.out.println("FIND BY ID::::");
        System.out.println(account);
        return account;
    }

    @GetMapping("/account/exist")
    public boolean accountAlreadyExists(@RequestParam(name = "email") String email) {
        Account account = accountService.findAccountByEmail(email);

        return account != null ? true : false;
    }
}
