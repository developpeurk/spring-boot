package com.lambarki.yassine.ebank_lambarki_yassine.repositories;

import com.lambarki.yassine.ebank_lambarki_yassine.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
