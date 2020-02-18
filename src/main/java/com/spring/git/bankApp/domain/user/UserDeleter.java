package com.spring.git.bankApp.domain.user;

public interface UserDeleter {

    void deleteIdUser(long userId);
    void deleteUserWIthUsername(String username);
}
