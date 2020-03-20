package edu.rimand.controller;

import edu.rimand.domain.Role;
import edu.rimand.domain.User;
import edu.rimand.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(User user, Map<String, Object> model){//@RequestParam String username, @RequestParam String password, Map<String, Object> model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message", "user exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}