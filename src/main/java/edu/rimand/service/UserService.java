package edu.rimand.service;

import edu.rimand.domain.Role;
import edu.rimand.domain.User;
import edu.rimand.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Value("${server.address}")
    private String address;
    @Value("${server.port}")
    private String port;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

    public boolean addUser(User user){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            sendActivationCode(user);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) return false;
        user.setActivationCode(null);
        userRepo.save(user);
        return true;

    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).
                map(Role::name).
                collect(Collectors.toSet());


        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
    }

    public void updateProfile(User user, String password, String email) {
    //public void updateProfile(User user, String password, String passwordConfirm, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) || (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged){
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepo.save(user);
        if(isEmailChanged) {
            sendActivationCode(user);
        }
    }

    private void sendActivationCode(User user) {
        String message = String.format(
                "Hello, %s! \n Welcome to Tweater/ Please visit next link to activate your account:"+
                        "http://"+address+":"+port+"/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        mailSender.send(user.getEmail(), "Activation code", message);
    }
}