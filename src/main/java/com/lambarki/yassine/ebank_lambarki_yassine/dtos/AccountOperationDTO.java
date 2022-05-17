package com.lambarki.yassine.ebank_lambarki_yassine.dtos;

import com.lambarki.yassine.ebank_lambarki_yassine.enums.OperationType;
import lombok.Data;

import java.util.Date;

@Data

public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}
