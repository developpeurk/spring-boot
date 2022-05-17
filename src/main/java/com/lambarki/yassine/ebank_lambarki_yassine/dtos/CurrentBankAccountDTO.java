package com.lambarki.yassine.ebank_lambarki_yassine.dtos;

import com.lambarki.yassine.ebank_lambarki_yassine.enums.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data
public class CurrentBankAccountDTO {

    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
