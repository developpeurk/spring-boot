package com.lambarki.yassine.ebank_lambarki_yassine.repositories;

import com.lambarki.yassine.ebank_lambarki_yassine.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
