package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository=accountRepository;
    }

    public int createAccount(Account account)
    {
        if(account.getPassword().length()<4||account.getUsername().isEmpty())
        {
            return 0;
        }else if(accountRepository.findByUsername(account.getUsername()).isPresent())
        {
            return -1;
        }
        accountRepository.save(account);
        return 1;
    }

    public int accountLogin(Account account)
    {
        if(accountRepository.findByUsername(account.getUsername()).isEmpty())
        {
            return 0;
        }
        Account acc= accountRepository.findByUsername(account.getUsername()).get();
        if(acc.getPassword()!=account.getPassword())
        {
            return 0;
        }

        return 1;
    }
    
    public Account getAccountByUsername(String username)
    {
        return accountRepository.findByUsername(username).get();
    }
}
