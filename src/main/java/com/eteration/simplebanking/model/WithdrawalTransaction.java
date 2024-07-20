package com.eteration.simplebanking.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("WITHDRAWAL")
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Withdrawal of " + amount + " on " + date;
    }

    @Override
    public String getType() {
        return "WITHDRAWAL";
    }

    @Override
    public void transactionType(Account account) throws InsufficientBalanceException {
        account.debit(this);
    }

}