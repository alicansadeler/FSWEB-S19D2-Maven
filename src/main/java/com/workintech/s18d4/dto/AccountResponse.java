package com.workintech.s18d4.dto;

public record AccountResponse(long id, String accountName, double moneyAmount, CustomerResponse customerResponse) {
}
