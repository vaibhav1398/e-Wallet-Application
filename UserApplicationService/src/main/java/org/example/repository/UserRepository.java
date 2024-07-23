package org.example.repository;

import org.apache.logging.log4j.util.Strings;
import org.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User,Integer> {
    UserDetails findByUsername(String username);
}
