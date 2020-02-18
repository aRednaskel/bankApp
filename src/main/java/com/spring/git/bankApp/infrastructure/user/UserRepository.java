package com.spring.git.bankApp.infrastructure.user;

import com.spring.git.bankApp.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    void deleteByUsername(String username);
}
