package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping()
    public ResponseEntity<TransactionStatus> createAccount(@RequestBody String accountJson) throws JsonProcessingException {
        Account account = objectMapper.readValue(accountJson, Account.class);
        accountService.createAccount(account);
        TransactionStatus status = new TransactionStatus("OK", account.getId());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber, @RequestBody Transaction transaction) {
        Account account = accountService.findAccount(accountNumber);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        account.credit(transaction);
        accountService.updateAccount(account);
        TransactionStatus status = new TransactionStatus("OK", transaction.getId());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber, @RequestBody Transaction transaction) throws InsufficientBalanceException {
        Account account = accountService.findAccount(accountNumber);
        account.debit(transaction);
        accountService.updateAccount(account);
        TransactionStatus status = new TransactionStatus("OK", transaction.getId());
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        Account account = accountService.findAccount(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}