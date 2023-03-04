package com.alexjoiner.assignment13.service;

import com.alexjoiner.assignment13.domain.Account;
import com.alexjoiner.assignment13.domain.User;
import com.alexjoiner.assignment13.repository.AccountRepository;
import com.alexjoiner.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Set<User> findall() {
        return userRepository.findAllUserAccountsAndAddress();
    }

    public User findById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(new User());
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            Account checking = new Account();
            Account saving = new Account();
            checking.setAccountName("Checking Account");
            saving.setAccountName("Savings Account");
            checking.getUsers().add(user);
            saving.getUsers().add(user);
            user.getAccounts().add(checking);
            user.getAccounts().add(saving);
            accountRepository.save(checking);
            accountRepository.save(saving);
        }
        return userRepository.save(user);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
