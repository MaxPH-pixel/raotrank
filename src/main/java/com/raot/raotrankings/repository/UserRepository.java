package com.raot.raotrankings.repository;

import com.raot.raotrankings.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);
    boolean existsUsersByUsername(String username);

}
