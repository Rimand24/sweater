package edu.rimand.repository;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    List<Message> findByTag (String tag);
    List<Message> findByAuthor(User user);
}