package com.spring.git.bankApp.infrastructure.user;

import com.spring.git.bankApp.domain.user.UserDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleterImplementation implements UserDeleter {

    private final UserRepository userRepository;

    @Override
    public void deleteIdUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteUserWIthUsername(String username) {
        userRepository.deleteByUsername(username);
    }
}
