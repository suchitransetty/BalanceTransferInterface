package com.example.bank.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.BankAccount;
import com.example.bank.repository.BalanceTransferRepository;

@Service("clientService")
public class BalanceTransferService {
	
	@Autowired
	BalanceTransferRepository balacerepo;
	
	Logger log = LoggerFactory.getLogger(BalanceTransferService.class);
	
	public int createAccount (BankAccount acct) {
		log.info("In Balance Transfer Service"+acct);
		return balacerepo.createAccount(acct);
	}
	
	public BankAccount findByBankID(long acctId) {
		log.info("In Balance Transfer Service"+acctId);
		return balacerepo.findByBankID(acctId);	
	}

	public String balanceTransfer(int sAcctId, int rAcctId, int tamount) {
		log.info("In Balance Transfer Service : balanceTransfer");
		 BankAccount senderAcct = findByBankID(sAcctId);
		 log.info("Sender Details:"+senderAcct);
		 if(tamount != 0) {
			 if(senderAcct.getAccountBalance() >= tamount) {
				 BankAccount receiverAcct = findByBankID(rAcctId);
				 int amount = senderAcct.getAccountBalance()-tamount;
				 log.info("amount after deduction at sender :"+amount);
				 int value = balacerepo.updateAccount(sAcctId, amount);
				 if(value != 0) {
					int amt = receiverAcct.getAccountBalance()+tamount;
					log.info("amount after additon at receiver :"+amt);
					int val = balacerepo.updateAccount(rAcctId, amt);
					if(val == 1) {
						return "Success"; 
					}else {
						return "Amount deducted but not updated at receiver side";
					}
				 }else {
					 return "Error while transacting amount"; 
				 }
			 }else {
				 return "Insuffiect amount to transact";
			 }
		 }
		return "Please send the right amount to transact";
	}
	
}
