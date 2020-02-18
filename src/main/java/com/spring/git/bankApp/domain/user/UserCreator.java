package com.spring.git.bankApp.domain.user;

import com.spring.git.bankApp.domain.model.user.Gender;

public interface UserCreator {

    void create(String login, Gender gender, String password);

}
