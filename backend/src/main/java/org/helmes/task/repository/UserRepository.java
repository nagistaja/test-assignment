package org.helmes.task.repository;

import org.helmes.task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.sectors WHERE u.editToken = :editToken")
  Optional<User> findByEditToken(UUID editToken);
}
