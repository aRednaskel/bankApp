package com.spring.git.bankApp.domain.account;

public interface AccountDeleter {

    void deleteAccountWithId(long accountId);

    void deleteAccountWithAccountNumber(String accountNumber);
}
