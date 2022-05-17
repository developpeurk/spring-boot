package com.lambarki.yassine.ebank_lambarki_yassine.services;

import com.lambarki.yassine.ebank_lambarki_yassine.dtos.BankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.CurrentBankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.CustomerDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.SavingBankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.BankAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.CurrentAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.SavingAccount;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BankAccountNotFoundException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BlanceNotSufficientException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
     CustomerDTO saveCustomer(CustomerDTO customerDTO);
     CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     List<CustomerDTO> listCustomers();
     BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BlanceNotSufficientException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
     void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BlanceNotSufficientException;
     List<BankAccountDTO> bankAccountList();

     CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}
