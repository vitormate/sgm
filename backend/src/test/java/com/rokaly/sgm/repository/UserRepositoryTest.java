package com.rokaly.sgm.repository;

import com.rokaly.sgm.dto.RegisterRequest;
import com.rokaly.sgm.model.User;
import com.rokaly.sgm.utils.enums.RoleUser;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByLoginSucess() {
        RegisterRequest data = new RegisterRequest("vitor", "123456", RoleUser.ADMIN);
        User user = this.createUser(data);
        UserDetails result = this.userRepository.findByLogin(data.login());

        assertThat(result.getUsername().equals(user.getLogin()));

    }

    @Test
    void findByLoginError() {
        RegisterRequest data = new RegisterRequest("vitor", "123456", RoleUser.ADMIN);
        UserDetails result = this.userRepository.findByLogin(data.login());

        assertThat(result == null);

    }

    private User createUser(RegisterRequest data) {
        User user = new User(data.login(), data.password(), data.role());
        this.testEntityManager.persist(user);
        return user;
    }
}