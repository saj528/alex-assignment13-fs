package com.alexjoiner.assignment13.web;

import com.alexjoiner.assignment13.domain.User;
import com.alexjoiner.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String getCreateUser(ModelMap modelMap){

        modelMap.put("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user){

        userService.saveUser(user);

        return "redirect:/register";
    }


    @GetMapping("/users")
    private String getAllUsers(ModelMap modelMap){
        List<User> users = userService.findall();
        modelMap.put("users",users);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser(ModelMap modelMap, @PathVariable Long userId){
        User user = userService.findById(userId);
        modelMap.put("user",user);
        return "user";
    }

    @PostMapping("/users/{userId}")
    public String updateOneUser(@PathVariable Long userId, User user){
        user = userService.saveUser(user);

        return "redirect:/users/" + user.getId();
    }


}
