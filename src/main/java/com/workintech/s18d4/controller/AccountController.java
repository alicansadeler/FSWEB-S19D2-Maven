package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.dto.SingleRecordAccount;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exceptions.ApiException;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.AccountServiceImpl;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;
    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<SingleRecordAccount> getAccounts() {
        List<Account> accounts = accountService.findAll();
        List<SingleRecordAccount> accountResponses = new ArrayList<>();
        for (Account account: accounts) {
            accountResponses.add(new SingleRecordAccount(account.getId(), account.getAccountName(), account.getMoneyAmount()));
        }
        return accountResponses;
    }

    @GetMapping("/{id}")
    public SingleRecordAccount getAccount(@PathVariable("id") Long id) {
       Account account = accountService.find(id);
        return new SingleRecordAccount(account.getId(), account.getAccountName(), account.getMoneyAmount());
    }

    @DeleteMapping("/{id}")
    public SingleRecordAccount deleteAccount(@PathVariable("id") Long id) {
        Account account = accountService.find(id);
        accountService.delete(id);

        return new SingleRecordAccount(account.getId(), account.getAccountName(), account.getMoneyAmount());
    }

    @PostMapping("/{customerId}")
    public AccountResponse saveAddressToCustomer(@PathVariable("customerId") Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);

        customer.addAccount(account);
        customerService.save(customer);
        accountService.save(account);
        CustomerResponse customerResponse = new CustomerResponse(customerId, customer.getEmail(), customer.getSalary());
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), customerResponse);
    }


    @PutMapping("/{customerId}")
    public AccountResponse updateAddressToCustomer(@PathVariable("customerId") Long customerId, @RequestBody Account updateAccount) {
        Customer customer = customerService.find(customerId);

        for (Account account1: customer.getAccounts()) {
            if (account1.getId() == updateAccount.getId()) {
                account1.setAccountName(updateAccount.getAccountName());
                account1.setMoneyAmount(updateAccount.getMoneyAmount());
            }
        }
        customerService.save(customer);
        CustomerResponse customerResponse = new CustomerResponse(customerId, customer.getEmail(), customer.getSalary());
        return new AccountResponse(updateAccount.getId(), updateAccount.getAccountName(), updateAccount.getMoneyAmount(), customerResponse);
    }
}
