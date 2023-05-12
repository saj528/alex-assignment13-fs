package com.alexjoiner.assignment13.web;

import com.alexjoiner.assignment13.domain.Account;
import com.alexjoiner.assignment13.domain.Address;
import com.alexjoiner.assignment13.domain.User;
import com.alexjoiner.assignment13.service.AccountService;
import com.alexjoiner.assignment13.service.AddressService;
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
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String getCreateUser(ModelMap modelMap) {

        modelMap.put("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user) {

        userService.saveUser(user);

        return "redirect:/register";
    }


    @GetMapping("/users")
    private String getAllUsers(ModelMap modelMap) {
        Set<User> users = userService.findall();
        modelMap.put("users", users);
        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser(ModelMap modelMap, @PathVariable Long userId) {
        User user = userService.findById(userId);
        if (user.getAddress() == null) {
            Address address = new Address();
            address.setUser(user);
            user.setAddress(address);
            addressService.saveAddress(address);
            userService.saveUser(user);

        }

        modelMap.put("user", user);
        modelMap.put("address", user.getAddress());
        modelMap.put("accounts", user.getAccounts());
        return "user";
    }

    @PostMapping("/users/{userId}")
    public String updateOneUser(User user, Address address) {
        user.setAddress(address);
        address.setUser(user);
        if(user.getPassword().isEmpty()){
            user.setPassword(userService.findById(user.getId()).getPassword());
        }
        user = userService.saveUser(user);
        addressService.saveAddress(address);
        return "redirect:/users/" + user.getId();
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

    @PostMapping("/users/{userId}/create-account")
    public String createOneBankAccount(@PathVariable Long userId) {
        User user = userService.findById(userId);
        int numberOfAccounts = user.getAccounts().size() + 1;
        Account account = new Account();
        account.setAccountName("Account #" + numberOfAccounts);
        user.getAccounts().add(account);
        account.getUsers().add(user);
        accountService.saveAccount(account);
        return "redirect:/users/" + userId;
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public String getUsersAccount(@PathVariable Long accountId, ModelMap modelMap) {

        Account account = accountService.findById(accountId);
        modelMap.put("account",account);
        return "account";
    }

    @PostMapping("/users/{userId}/accounts/{accountId}")
    public String postUsersAccount(@PathVariable Long userId, @PathVariable Long accountId, Account account) {
        account.setId(accountId);
        accountService.saveAccount(account);

        return "redirect:/users/" + userId + "/accounts/" + accountId;
    }

}
