package com.lambarki.yassine.ebank_lambarki_yassine.services;

import com.lambarki.yassine.ebank_lambarki_yassine.entities.BankAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.CurrentAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.Customer;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.SavingAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BankAccountNotFoundException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BlanceNotSufficientException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
     Customer saveCustomer(Customer customer);
     CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     List<Customer> listCustomers();
     BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BlanceNotSufficientException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
     void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BlanceNotSufficientException;
}
