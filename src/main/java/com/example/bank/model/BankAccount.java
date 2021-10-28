package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class BankAccount {
	@Id
	@Column
    private long accountNumber;
	@Column
    private String accountHolderName;
	@Column
    private String BankName;
	@Column
    private String IFSCCode;
	@Column
    private int accountBalance;
	@Column
	private String accountType;
    	
	public BankAccount(long accountNumber, String accountHolderName, String bankName, String iFSCCode,
			int accountBalance, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		BankName = bankName;
		IFSCCode = iFSCCode;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	
	public String getIFSCCode() {
		return IFSCCode;
	}
	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", BankName=" + BankName + ", IFSCCode=" + IFSCCode + ", accountBalance=" + accountBalance
				+ ", accountType=" + accountType + "]";
	}

}
