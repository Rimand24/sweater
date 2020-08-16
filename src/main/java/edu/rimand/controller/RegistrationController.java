package edu.rimand.controller;

import edu.rimand.Dto.CaptchaResponseDto;
import edu.rimand.domain.User;
import edu.rimand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Value("${recaptcha.secret}")
    private String captchaSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(@RequestParam("password2") String passwordConfirm,
                      @RequestParam("g-recaptcha-response") String captchaResponse,
                      @Valid User user,
                      BindingResult bindingResult,
                      Model model) {

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if(isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation can't be empty");
        }

        if(user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            model.addAttribute("password2Error", "password are different");
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors()){
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        String url = String.format(CAPTCHA_URL, captchaSecret, captchaResponse);
        CaptchaResponseDto responseDto = restTemplate.postForObject(url, Collections.EMPTY_LIST, CaptchaResponseDto.class);

        if (responseDto==null || !responseDto.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha");
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "user exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "user activated");
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "activation code is not found");
            model.addAttribute("messageType", "danger");
        }
        return "login";
    }
}