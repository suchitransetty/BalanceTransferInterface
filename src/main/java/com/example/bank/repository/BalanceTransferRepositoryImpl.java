package com.example.bank.repository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.bank.model.BankAccount;
@Repository
public class BalanceTransferRepositoryImpl implements BalanceTransferRepository{

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	Logger log = LoggerFactory.getLogger(BalanceTransferRepositoryImpl.class);
	
	@SuppressWarnings("deprecation")
	@Override
	public BankAccount findByBankID(long accountid) {
		log.info("In Balance Transfer repository : findByBankID");
		return jdbcTemplate.queryForObject(
                "select * from BANK_ACCOUNT where ACCOUNT_NUMBER = ?",
                new Object[]{accountid},
                (rs, rowNum) ->
                        new BankAccount(
                        		rs.getLong("ACCOUNT_NUMBER"),
                        		rs.getString("ACCOUNT_HOLDER_NAME"),
                        		rs.getString("BANK_NAME"),
                        		rs.getString("IFSCCODE"),
                        		rs.getInt("ACCOUNT_BALANCE"),
                        		rs.getString("ACCOUNT_TYPE")
                        )
        );
	}

	@Override
	public int createAccount(BankAccount acct) {
		log.info("In Balance Transfer repository : createAccount");
		
		 return  jdbcTemplate.update(
	                "insert into BANK_ACCOUNT (ACCOUNT_NUMBER,ACCOUNT_HOLDER_NAME,BANK_NAME,IFSCCODE,ACCOUNT_BALANCE,ACCOUNT_TYPE) values(?,?,?,?,?,?)",
	                acct.getAccountNumber(),acct.getAccountHolderName(),acct.getBankName(),acct.getIFSCCode(),acct.getAccountBalance(),acct.getAccountType());
	}

	@Override
	public int updateAccount(int acctNum , int balance) {
		log.info("In Balance Transfer repository : updateAccount");
		return jdbcTemplate.update("Update BANK_ACCOUNT set ACCOUNT_BALANCE ="+balance+" where ACCOUNT_NUMBER = "+acctNum);
	}

}
