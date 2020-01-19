package com.spring.git.bankApp.infrastructure.user;

import com.spring.git.bankApp.domain.model.user.Gender;
import com.spring.git.bankApp.domain.user.UserCommand;
import com.spring.git.bankApp.domain.user.UserCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserPostgresCreator implements UserCreator {

    private final UserRepository userRepository;

    @Override
    public void createUser(String login, Gender gender) {
        UserCommand userCommand = UserCommand.builder().login(login)
                .gender(gender).build();
        userRepository.save(userCommand.createUser());
    }

}
