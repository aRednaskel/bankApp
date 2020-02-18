package com.spring.git.bankApp.api.user;

import com.spring.git.bankApp.domain.model.user.User;
import com.spring.git.bankApp.domain.user.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

    private final UserFacade userFacade;

    @GetMapping(path = "/{login}")
    public ResponseEntity<UserDto> findByLogin(@PathVariable String login) {
        return ResponseEntity.ok(UserMapper.mapToDto(userFacade.loadUserByUsername(login)));
    }

    @GetMapping(path = "/all")
    public Iterable<UserDto> findAllusers() {
        return UserMapper.mapIterableUsersToDto(userFacade.findAllUsers());
    }

    @PatchMapping(path = "/updateLogin")
    public void udpateLogin(@RequestParam String password, @RequestParam String oldLogin,
                            @NotBlank @Size(min = 3, max = 15) @RequestParam String newLogin) {
        log.info("Updating login {}, newLogin {}", oldLogin, newLogin);
        userFacade.updateLogin(oldLogin, newLogin, password);
    }

    @PatchMapping(path = "/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String login, @RequestParam String oldPassword,
                               @NotBlank @Size(min = 6, max = 15) @RequestParam String newPassword) {
        log.info("Updating password {}", login);
        userFacade.updatePassword(login, oldPassword, newPassword);
        return  ResponseEntity.ok("Jest ok");
    }

    @PostMapping(path = "user")
    public void createUser(@Valid @RequestBody UserDto userDto) {
        log.info("User creation {}", userDto.getLogin());
        userFacade.createUser(userDto.getLogin(), userDto.getGender(), userDto.getPassword());
    }

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUserWithId(@PathVariable long userId) {
        log.info("Deleting user with Id ", userId);
        userFacade.deleteUserById(userId);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUserWithUsername(@RequestParam String username) {
        log.info("Deleting user with username ", username);
        userFacade.deleteByUsername(username);
    }

    private static class UserMapper {
        private static UserDto mapToDto(User user) {
            return UserDto.builder().login(user.getUsername())
                    .gender(user.getGender()).build();
        }

        private static Iterable<UserDto> mapIterableUsersToDto(List<User> users) {
            List<UserDto> dtoUsers = users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());
            return dtoUsers;
        }
    }

}
