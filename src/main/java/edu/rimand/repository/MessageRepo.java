package edu.rimand.repository;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;

public interface MessageRepo extends CrudRepository<Message, Long> {
    Page<Message> findByTag (String tag, Pageable pageable);
    Page<Message> findAll (Pageable pageable);
    Page<Message> findByAuthor(User user, Pageable pageable);
}