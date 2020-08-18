package edu.rimand.controller;

import edu.rimand.domain.Role;
import edu.rimand.domain.User;
import edu.rimand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userlist(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam String username,
            @RequestParam Map<String, String> form
    ) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(Model model,
                                @AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {

        userService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }

    @GetMapping("/subscribe/{user}")
    public String subscribe(@AuthenticationPrincipal User currentUser, @PathVariable User user) {
        System.out.println(user.getId());
        userService.subscribe(currentUser, user);
        System.out.println("redirect:/user-messages/" + user.getId());
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/unsubscribe/{user}")
    public String unsubscribe(@AuthenticationPrincipal User currentUser, @PathVariable User user) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String userList(@PathVariable String type, @PathVariable User user, Model model) {

        if ("subscriptions".equals(type)) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }

        model.addAttribute("type", type);
        model.addAttribute("userChannel", user);

        return "subscriptions";
    }
}