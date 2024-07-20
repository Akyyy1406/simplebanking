package com.eteration.simplebanking.controller;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
public class TransactionStatus {
    private String status;
    private UUID approvalCode;

    public TransactionStatus(String status,UUID approvalCode) {
        this.status = status;
        this.approvalCode = approvalCode;
    }


}