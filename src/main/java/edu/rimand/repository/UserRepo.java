package edu.rimand.repository;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
    List<User> findAllByActive(boolean active);
}

