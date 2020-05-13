package edu.rimand.Dto;

import edu.rimand.domain.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {

        private String username;
        private String password;
        private boolean active;
        private String email;
        private String activationCode;
        private Set<Role> roles;
}
