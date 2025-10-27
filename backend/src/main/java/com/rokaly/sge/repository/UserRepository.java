package com.rokaly.sge.repository;

import com.rokaly.sge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
