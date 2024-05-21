package com.vti.ecommerce.config.dataInitializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vti.ecommerce.domains.entities.User;
import com.vti.ecommerce.repositories.UserRepository;

@Component
public class UserDataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // // Tạo người dùng mặc định
        // if (userRepository.findByUserName("user").equals(null)) {
        //     User user = new User();
        //     user.setUserName("user");
        //     user.setPassword(passwordEncoder.encode("password"));
        //     userRepository.save(user);
        // }
    }
}
