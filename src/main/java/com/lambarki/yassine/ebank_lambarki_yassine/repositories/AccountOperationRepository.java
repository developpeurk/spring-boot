package com.lambarki.yassine.ebank_lambarki_yassine.repositories;

import com.lambarki.yassine.ebank_lambarki_yassine.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
