package com.eteration.simplebanking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

// This class is a place holder you can change the complete implementation
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "transaction")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WithdrawalTransaction.class, name = "WITHDRAWAL"),
        @JsonSubTypes.Type(value = DepositTransaction.class, name = "DEPOSIT"),
        @JsonSubTypes.Type(value = SalaryTransaction.class, name = "SALARY"),
        @JsonSubTypes.Type(value = BillPaymentTransaction.class, name = "BILL")
})
public abstract class Transaction {
    @Id
    @Type(type="uuid-char")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "amount", nullable = false)
    protected double amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    @Column(name = "date", nullable = false)
    protected LocalDateTime date;

    @Column(name = "type", nullable = false, insertable = false, updatable = false)
    protected String type;

    public Transaction(double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    public abstract void transactionType(Account account) throws InsufficientBalanceException;

    public abstract String getType();


}
