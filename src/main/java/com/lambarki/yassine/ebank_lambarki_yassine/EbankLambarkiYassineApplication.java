package com.lambarki.yassine.ebank_lambarki_yassine;

import com.lambarki.yassine.ebank_lambarki_yassine.dtos.BankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.CurrentBankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.CustomerDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.dtos.SavingBankAccountDTO;
import com.lambarki.yassine.ebank_lambarki_yassine.entities.*;
import com.lambarki.yassine.ebank_lambarki_yassine.enums.AccountStatus;
import com.lambarki.yassine.ebank_lambarki_yassine.enums.OperationType;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BankAccountNotFoundException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.BlanceNotSufficientException;
import com.lambarki.yassine.ebank_lambarki_yassine.exceptions.CustomerNotFoundException;
import com.lambarki.yassine.ebank_lambarki_yassine.repositories.AccountOperationRepository;
import com.lambarki.yassine.ebank_lambarki_yassine.repositories.BankAccountRepository;
import com.lambarki.yassine.ebank_lambarki_yassine.repositories.CustomerRepository;
import com.lambarki.yassine.ebank_lambarki_yassine.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankLambarkiYassineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankLambarkiYassineApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunnerTest(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan", "Imane", "Mohamed").forEach(name->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {

                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000, 9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*12000, 5.5, customer.getId());
                    List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
                    for(BankAccountDTO bankAccount: bankAccounts){
                        for (int i = 0; i < 10; i++) {
                            String accountId;
                            if(bankAccount instanceof SavingBankAccountDTO){
                                accountId=((SavingBankAccountDTO) bankAccount).getId();
                            }else{
                                accountId=((CurrentBankAccountDTO) bankAccount).getId();
                            }
                            bankAccountService.credit(accountId,10000+Math.random()*1200, "Credit");
                            bankAccountService.debit(accountId, 1000+Math.random()*900 , "Debit");
                        }
                    }
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                } catch (BankAccountNotFoundException | BlanceNotSufficientException e) {
                    e.printStackTrace();
                }
            });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository){
        return args -> {
            BankAccount bankAccount= bankAccountRepository.findById("00b8c49d-b34f-4053-a922-8ee0cbbce9db").orElse(null);
            System.out.println("************************");
            if(bankAccount!=null){
                System.out.println(bankAccount.getId());
                System.out.println(bankAccount.getBalance());
                System.out.println(bankAccount.getStatus());
                System.out.println(bankAccount.getCreatedAt());
                System.out.println(bankAccount.getCustomer().getName());
                System.out.println(bankAccount.getClass().getSimpleName());
                if(bankAccount instanceof CurrentAccount){
                    System.out.println("OverDraft=> " + ((CurrentAccount)bankAccount).getOverDraft());
                }else   if(bankAccount instanceof SavingAccount){
                    System.out.println("Rate=> " + ((SavingAccount)bankAccount).getInterestRate());
                }
                bankAccount.getAccountOperations().forEach(op-> {
                    System.out.println(op.getType() + "\t" + op.getAmount() + "\t" +  op.getOperationDate());
                });
            }
        };
    }

        //@Bean
        CommandLineRunner start(CustomerRepository customerRepository,
                                BankAccountRepository bankAccountRepository,
                                AccountOperationRepository accountOperationRepository){
            return args->{
                Stream.of("Hassan", "Yassine", "Aicha").forEach(name-> {
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setEmail(name+"@gmail.com");
                    customerRepository.save(customer);
                });
                customerRepository.findAll().forEach(cust-> {
                    CurrentAccount currentAccount = new CurrentAccount();
                    currentAccount.setId(UUID.randomUUID().toString());
                    currentAccount.setBalance(Math.random()*90000);
                    currentAccount.setCreatedAt(new Date());
                    currentAccount.setStatus(AccountStatus.CREATED);
                    currentAccount.setCustomer(cust);
                    currentAccount.setOverDraft(9000);
                    bankAccountRepository.save(currentAccount);

                    SavingAccount savingAccount = new SavingAccount();
                    savingAccount.setId(UUID.randomUUID().toString());
                    savingAccount.setBalance(Math.random()*90000);
                    savingAccount.setCreatedAt(new Date());
                    savingAccount.setStatus(AccountStatus.CREATED);
                    savingAccount.setCustomer(cust);
                    savingAccount.setInterestRate(5.5);
                    bankAccountRepository.save(savingAccount);
                });
                bankAccountRepository.findAll().forEach(acc->{
                    for (int i = 0; i < 10; i++) {
                        AccountOperation accountOperation = new AccountOperation();
                        accountOperation.setOperationDate(new Date());
                        accountOperation.setAmount(Math.random()*12000);
                        accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                        accountOperation.setBankAccount(acc);
                        accountOperationRepository.save(accountOperation);

                    }
                });
            };

    }
}


