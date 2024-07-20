package com.eteration.simplebanking.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("BILL")
public class BillPaymentTransaction extends WithdrawalTransaction {
    private String payee;
    private String phoneNumber;

    @Builder
    public BillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount);
        this.payee = payee;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Bill Payment of " + amount + " to " + payee + " on " + date;
    }

    @Override
    public String getType() {
        return "BILL";
    }

    @Override
    public void transactionType(Account account) throws InsufficientBalanceException {
        super.transactionType(account);
    }

}