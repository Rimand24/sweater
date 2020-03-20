package edu.rimand.controller;

import edu.rimand.domain.Message;
import edu.rimand.domain.User;
import edu.rimand.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping ("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping ("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Message msg = new Message(user, text, tag);
        messageRepo.save(msg);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping ("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findByTag(filter);
        messages = filter.isEmpty() ? messageRepo.findAll() : messageRepo.findByTag(filter);
        model.put("messages", messages);
        return "main";
    }
}