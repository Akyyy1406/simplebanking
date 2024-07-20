package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ModelTest {
	
	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() throws Exception {
		Account account = new Account("Demet Demircan", "9834");
		DepositTransaction depositTransaction = new DepositTransaction(100);
		account.credit(depositTransaction);
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws Exception {
		Account account = new Account("Demet Demircan", "9834");
		DepositTransaction depositTransaction = new DepositTransaction(100);
		account.credit(depositTransaction);
		assertTrue(account.getBalance() == 100);
		WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(50);
		account.debit(withdrawalTransaction);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			DepositTransaction depositTransaction = new DepositTransaction(100);
			account.credit(depositTransaction);
			WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(500);
			account.debit(withdrawalTransaction);
		  });

	}
	
	@Test
	public void testTransactions() throws Exception {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		assertTrue(depositTrx.getDate() != null);
		account.post(depositTrx);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx);
		assertTrue(account.getBalance() == 40);
		assertTrue(account.getTransactions().size() == 2);
	}

	@Test
	public void task1() throws InsufficientBalanceException {
		Account account = new Account("Jim", "12345");
		account.post(new DepositTransaction(1000));
		account.post(new WithdrawalTransaction(200));
		account.post(new BillPaymentTransaction("Vodafone", "5423345566", 96.50));
		assertEquals(account.getBalance(), 703.50, 0.0001);
	}
}
