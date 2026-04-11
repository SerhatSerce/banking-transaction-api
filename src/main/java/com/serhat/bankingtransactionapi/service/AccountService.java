package com.serhat.bankingtransactionapi.service;

import com.serhat.bankingtransactionapi.dto.AccountResponse;
import com.serhat.bankingtransactionapi.dto.CreateAccountRequest;
import com.serhat.bankingtransactionapi.dto.DepositRequest;
import com.serhat.bankingtransactionapi.dto.TransferRequest;
import com.serhat.bankingtransactionapi.dto.WithdrawRequest;
import com.serhat.bankingtransactionapi.entity.Account;
import com.serhat.bankingtransactionapi.exception.AccountNotFoundException;
import com.serhat.bankingtransactionapi.exception.DuplicateAccountNumberException;
import com.serhat.bankingtransactionapi.exception.InsufficientBalanceException;
import com.serhat.bankingtransactionapi.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {
        if (accountRepository.findByAccountNumber(request.getAccountNumber()).isPresent()) {
            throw new DuplicateAccountNumberException(
                    "Account number already exists: " + request.getAccountNumber()
            );
        }

        Account account = new Account();
        account.setAccountNumber(request.getAccountNumber());
        account.setOwnerName(request.getOwnerName());
        account.setBalance(request.getBalance());

        return toResponse(accountRepository.save(account));
    }

    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AccountResponse getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

        return toResponse(account);
    }

    public AccountResponse deposit(DepositRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + request.getAccountId()));

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero");
        }

        account.setBalance(account.getBalance().add(request.getAmount()));
        return toResponse(accountRepository.save(account));
    }

    public AccountResponse withdraw(WithdrawRequest request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + request.getAccountId()));

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Withdraw amount must be greater than zero");
        }

        if (account.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(request.getAmount()));
        return toResponse(accountRepository.save(account));
    }

    @Transactional
    public String transfer(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found with id: " + request.getFromAccountId()));

        Account toAccount = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found with id: " + request.getToAccountId()));

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return "Transfer successful";
    }

private AccountResponse toResponse(Account account) {
    return new AccountResponse(
            account.getId(),
            account.getAccountNumber(),
            account.getOwnerName(),
            account.getBalance(),
            account.getCreatedAt()
    );
    }
}