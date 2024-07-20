package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface AccountRepository extends JpaRepository<Account, UUID> {

    Account findByAccountNumber(String accountNumber);
}
