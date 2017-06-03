
package com.playground.playground.servicesImpl;

import com.playground.playground.entities.User;
import com.playground.playground.models.binding.RegistrationModel;
import com.playground.playground.repositories.UserRepository;
import com.playground.playground.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(RegistrationModel registrationModel) {
        String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
        User user = new User();

        user.setUsername(registrationModel.getUsername());
        user.setPassword(encryptedPassword);

        userRepository.save(user);
    }
}