package com.spring.git.bankApp.domain.user;

import com.spring.git.bankApp.domain.model.user.Gender;
import com.spring.git.bankApp.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade implements UserDetailsService {

    private final UserCreator userCreator;
    private final UserRetrievalClient userRetrievalClient;
    private final UserUpdate userUpdate;
    private final UserDeleter userDeleter;

    public void createUser(String login, Gender gender, String password) {
        userCreator.create(login, gender, password);
    }

    public void updatePassword(String login, String oldPassword, String newPassword) {
        userUpdate.updatePassword(login, oldPassword, newPassword);
    }

    public void updateLogin(String oldLogin, String newLogin, String password) {
        userUpdate.updateLogin(oldLogin, newLogin, password);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRetrievalClient.getByLogin(username);
    }

    public List<User> findAllUsers() {
        return userRetrievalClient.findAll();
    }

    public void deleteUserById(long userId) {
        userDeleter.deleteIdUser(userId);
    }

    public void deleteByUsername(String username) {
        userDeleter.deleteUserWIthUsername(username);
    }
}