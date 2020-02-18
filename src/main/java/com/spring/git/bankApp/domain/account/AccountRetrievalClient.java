package com.spring.git.bankApp.domain.account;

import com.spring.git.bankApp.domain.model.account.Account;

import java.util.List;

public interface AccountRetrievalClient {


    Account findByAccountNumber(String accountNumber);

    Account findByAccountId(long id);

    Account findByAccNumberOrExtAccount(String accountNumber);

    List<Account> findAllAccounts();

}
