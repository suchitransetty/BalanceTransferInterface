package com.example.bank.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BalanceTransferService;

@RestController
public class BalanceTransferController {
	
	 Logger log = LoggerFactory.getLogger(BalanceTransferController.class);

	@Autowired(required = true)
	BalanceTransferService balanceService;
	
	@GetMapping("/balanceCheck/{acctId}")
	private ResponseEntity<Object> balanceCheck(@PathVariable long acctId) {
		log.info("BalanceCheck for account id :"+acctId);
	   BankAccount account = balanceService.findByBankID(acctId);
	   if(account != null)
	   return ResponseEntity.ok().body(account);
	   else
		   return new ResponseEntity<Object>("Account not found for this id :"+acctId,HttpStatus.NOT_FOUND);  
	}
	
	@PostMapping("/createAccount/")
	private ResponseEntity<Object> CreateAccount(@RequestBody BankAccount acct) {
		log.info("Bank Account details :"+acct);
		int value=  balanceService.createAccount(acct);
		if(value != 0)
		return new ResponseEntity<Object>(value,HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Sorry! Account was not created please try again",HttpStatus.NOT_IMPLEMENTED);
			
	}
	
	@PutMapping("/balanceTransfer/")
	private  ResponseEntity<Object> BalanceTransfer(@RequestParam int senderAcct, @RequestParam int recieverAcct ,@RequestParam int transferAmount) {
		log.info("BalanceTransfer Method SenderAccountNumber :"+senderAcct+", RecieverAccount"+recieverAcct+",transferable amount"+transferAmount);
		String resultstr = balanceService.balanceTransfer(senderAcct,recieverAcct,transferAmount);
		return new ResponseEntity<Object>(resultstr,HttpStatus.OK);
	}	
}
