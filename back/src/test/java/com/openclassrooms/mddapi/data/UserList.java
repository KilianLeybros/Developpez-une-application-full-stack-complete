package com.openclassrooms.mddapi.data;

import com.openclassrooms.mddapi.data.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    public static List<User> init(){
        List<User> userList = new ArrayList<User>();
        userList.add(new User().setId(1L).setUsername("user1").setEmail("user1@test.com").setPassword("password").setCreatedAt(LocalDateTime.now()));
        userList.add(new User().setId(2L).setUsername("user2").setEmail("user2@test.com").setPassword("password").setCreatedAt(LocalDateTime.now()));
        return  userList;
    }
}
