package com.eteration.simplebanking.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("SALARY")
public class SalaryTransaction extends DepositTransaction {

    private String company;

    @Builder
    public SalaryTransaction(String company, double amount) {
        super(amount);
        this.company = company;
    }

    @Override
    public String toString() {
        return "Salary of " + company + amount + " on " + date;
    }

    @Override
    public String getType() {
        return "SALARY";
    }

    @Override
    public void transactionType(Account account) {
        account.credit(this);
    }


}