package com.alexjoiner.assignment13.service;

import com.alexjoiner.assignment13.domain.User;
import com.alexjoiner.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findall() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(new User());
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
