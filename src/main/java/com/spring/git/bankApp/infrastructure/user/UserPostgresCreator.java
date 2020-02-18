package com.spring.git.bankApp.infrastructure.user;

import com.spring.git.bankApp.domain.model.user.Gender;
import com.spring.git.bankApp.domain.model.user.User;
import com.spring.git.bankApp.domain.user.UserCommand;
import com.spring.git.bankApp.domain.user.UserCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserPostgresCreator implements UserCreator {

    private final UserRepository userRepository;

    @Override
    public void create(String login, Gender gender, String password) {
        UserCommand userCommand = UserCommand.builder()
                .login(login)
                .gender(gender)
                .password(passwordEncoder().encode(password)).build();
        userRepository.save(userCommand.createUser());
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void userExamples() {
        create("John", Gender.MALE, "John");
        create("Robert", Gender.MALE, "Robert");
        create("Natalie", Gender.FEMALE, "Natalie");
        create("Karen", Gender.FEMALE, "Karen");

        User adminKaty = User.createAdmin("Katy", Gender.FEMALE, passwordEncoder().encode("Katy"));
        userRepository.save(adminKaty);
        User adminAleksander = User.createAdmin("Aleksander", Gender.MALE, passwordEncoder().encode("Aleksander"));
        userRepository.save(adminAleksander);
    }


}
