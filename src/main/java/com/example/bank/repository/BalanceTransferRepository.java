package com.example.bank.repository;




import com.example.bank.model.BankAccount;

public interface BalanceTransferRepository
{
	BankAccount findByBankID(long accountId);
   
   int createAccount(BankAccount acct);
   
   int updateAccount(int acctNum , int balance);
   
}
