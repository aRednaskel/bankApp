package com.spring.git.bankApp.domain.account;

import com.spring.git.bankApp.domain.model.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountCreator accountCreator;
    private final AccountRetrievalClient accountRetrievalClient;
    private final AccountDeleter accountDeleter;

    public void createPremiumToUser(long userId) {
        accountCreator.createPremiumToUser(userId);
    }

    public void createStandardToUser(long userId) {
        accountCreator.createStandardToUser(userId);
    }

    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRetrievalClient.findByAccountNumber(accountNumber);
    }

    public void deleteAccountWithId(long accountId) {
        accountDeleter.deleteAccountWithId(accountId);
    }

    public void deleteAccountWithAccountNumber(String accountNumber) {
        accountDeleter.deleteAccountWithAccountNumber(accountNumber);
    }

    public List<Account> findAllAccounts() {
        return accountRetrievalClient.findAllAccounts();
    }
}
