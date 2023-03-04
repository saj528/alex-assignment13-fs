package com.alexjoiner.assignment13.service;

import com.alexjoiner.assignment13.domain.Account;
import com.alexjoiner.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account) {

        return accountRepository.save(account);

    }

    public Account findById(Long accountId) {
        return accountRepository.findById(accountId).get();
    }

}
