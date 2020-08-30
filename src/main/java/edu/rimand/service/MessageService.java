package edu.rimand.service;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import edu.rimand.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public Page<Message> messageList(Pageable pageable, String filter) {
        if (filter != null && !filter.isEmpty()) {
            return messageRepo.findByTag(filter, pageable);
        } else {
            return messageRepo.findAll(pageable);
        }
    }

    public Page<Message> userListForUser(Pageable pageable, User author) {
        return messageRepo.findByUser(pageable, author);
    }
}
