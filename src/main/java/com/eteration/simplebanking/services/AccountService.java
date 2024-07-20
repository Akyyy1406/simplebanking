package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


// This class is a place holder you can change the complete implementation
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public void createAccount(Account account) {
        account.setCreateDate(LocalDateTime.now());
        //account.getTransactions().forEach(transaction -> transaction.setAccount(account));
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        account.getTransactions().forEach(transaction -> transaction.setAccount(account));
        accountRepository.save(account);
    }
}
