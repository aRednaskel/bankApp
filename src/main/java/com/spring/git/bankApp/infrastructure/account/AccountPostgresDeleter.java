package com.spring.git.bankApp.infrastructure.account;

import com.spring.git.bankApp.domain.account.AccountDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountPostgresDeleter implements AccountDeleter {

    private final AccountRepository accountRepository;

    @Override
    public void deleteAccountWithId(long accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public void deleteAccountWithAccountNumber(String accountNumber) {
        accountRepository.deleteByAccountNumber(accountNumber);
    }
}
