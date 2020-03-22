package edu.rimand.controller;

import edu.rimand.domain.User;
import edu.rimand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(@Valid User user, BindingResult bindingResult,  Model model) {
        if(user.getPassword() != null && !user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "password are diferent");
        }
        if (bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("message", "user exists");
            return "registration";
        }
            return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model){
        boolean isActivated = userService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "user activated");
        } else {
            model.addAttribute("message", "activation code is not found");
        }
        return "login";
    }
}