package com.lambarki.yassine.ebank_lambarki_yassine.dtos;

import lombok.Data;

@Data
public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;
}
