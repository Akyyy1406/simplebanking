package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account{
    @Id
    @Type(type = "uuid-char")
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private UUID id;
    @Column(name = "owner", nullable = false)
    private String owner;
    @Column(name = "account_number", nullable = false)
    private String accountNumber;
    @Column(name = "balance", nullable = false)
    private double balance;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void credit(Transaction transaction) {
        balance += transaction.amount;
        setTransaction(transaction);
        transactions.add(transaction);
    }

    public void debit(Transaction transaction) throws InsufficientBalanceException {
        if (balance >= transaction.getAmount()) {
            balance -= transaction.getAmount();
            setTransaction(transaction);
            transactions.add(transaction);
        } else {
            throw new InsufficientBalanceException("Insufficient funds");
        }
    }

    private static void setTransaction(Transaction transaction) {
        transaction.setDate(LocalDateTime.now());
        transaction.setType(transaction.getType());
        transaction.setId(UUID.randomUUID());
    }


    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.transactionType(this);
    }

}
