package com.lambarki.yassine.ebank_lambarki_yassine.repositories;

import com.lambarki.yassine.ebank_lambarki_yassine.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findByNameContains(String keyword);
}
