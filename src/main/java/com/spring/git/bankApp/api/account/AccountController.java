package com.spring.git.bankApp.api.account;

import com.spring.git.bankApp.domain.account.AccountFacade;
import com.spring.git.bankApp.domain.model.account.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/accounts")
@Validated
public class AccountController {

    private final AccountFacade accountFacade;

    @PostMapping("/premium/users/{userId}")
    public void createPremiumAccount(@PathVariable @Min(1) Long userId) {
        log.info("Account creation for userId {}", userId);
        accountFacade.createPremiumToUser(userId);
    }
    @PostMapping("/standard/users/{userId}")
    public void createStandardAccount(@PathVariable @Min(1) Long userId) {
        log.info("Account creation for userId {}", userId);
        accountFacade.createStandardToUser(userId);
    }

    @GetMapping(path = "/{accountNumber}")
    public ResponseEntity<AccountDto> findCardByAccountNumber(@Size(min = 16, max = 16) @PathVariable String accountNumber) {
        AccountDto accountDto = AccountMapper.mapToDto(accountFacade.findAccountByAccountNumber(accountNumber));
        return ResponseEntity.ok(accountDto);
    }
    @GetMapping(path = "/all")
    public Iterable<AccountDto> findAllusers() {
        return AccountMapper.mapIterableUsersToDto(accountFacade.findAllAccounts());
    }

    @DeleteMapping("/deleteAccount/{accountId}")
    public void deleteUserWithId(@PathVariable long accountId) {
        log.info("Deleting user with Id ", accountId);
        accountFacade.deleteAccountWithId(accountId);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUserWithUsername(@RequestParam String accountNumber) {
        log.info("Deleting user with accountNumber ", accountNumber);
        accountFacade.deleteAccountWithAccountNumber(accountNumber);
    }

    private static class AccountMapper {
        static AccountDto mapToDto(Account account) {
            return AccountDto.builder()
                    .accountNumber(account.getAccountNumber())
                    .balance(account.getBalance())
                    .build();
        }

        static Iterable<AccountDto> mapIterableUsersToDto(List<Account> accounts) {
            List<AccountDto> dtoAccounts = accounts.stream().map(account -> mapToDto(account)).collect(Collectors.toList());
            return dtoAccounts;
        }
    }

}
