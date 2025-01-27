package com.ghanshyam.test.test;

import com.ghanshyam.test.test.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
