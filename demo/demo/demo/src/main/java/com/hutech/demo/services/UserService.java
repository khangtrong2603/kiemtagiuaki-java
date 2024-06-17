package com.hutech.demo.services;

import com.hutech.demo.entity.User;
import com.hutech.demo.repository.IRoleRepository;
import com.hutech.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    public void save(User user) {
        try {
            userRepository.save(user);
            Long userId = userRepository.getUserIdByUsername(user.getUsername());
            Long role = roleRepository.getRoleIdByName("USER");
            if (role != 0 && userId != 0)
                userRepository.addRoleToUser(userId,role);
        } catch (Exception e) {
            // Log the exception details (you can use any logging framework)
            System.err.println("An error occurred while saving the user: " + e.getMessage());
            // Re-throw if necessary or handle accordingly
            throw e;
        }
    }
}
