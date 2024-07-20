package com.eteration.simplebanking.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

// This class is a place holder you can change the complete implementation

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("DEPOSIT")
public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public String toString() {
        return "Deposit of " + amount + " on " + date;
    }
    @Override
    public String getType() {
        return "DEPOSIT";
    }

    @Override
    public void transactionType(Account account) {
        account.credit(this);
    }

}