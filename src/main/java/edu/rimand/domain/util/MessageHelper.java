package edu.rimand.domain.util;

import edu.rimand.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User user){
        return user != null ? user.getUsername() : "<none>";
    }
}
