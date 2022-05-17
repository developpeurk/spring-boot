package com.lambarki.yassine.ebank_lambarki_yassine.web;

import com.lambarki.yassine.ebank_lambarki_yassine.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountRestController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


}
