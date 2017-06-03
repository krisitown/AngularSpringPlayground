package com.playground.playground.controllers;

import com.playground.playground.entities.User;
import com.playground.playground.models.binding.LoginModel;
import com.playground.playground.models.binding.RegistrationModel;
import com.playground.playground.repositories.UserRepository;
import com.playground.playground.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginModel loginModel, HttpSession session)
    {
        User user = userRepository.findOneByName(loginModel.getUsername());
        if(user == null)
        {
            throw new NullPointerException("User not found");
        }
        if(passwordEncoder.matches(loginModel.getPassword(), user.getPassword())){
            session.setAttribute("CURRENT_USER", user);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session){
        session.setAttribute("CURRENT_USER", null);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegistrationModel registrationModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }

        this.userService.register(registrationModel);

        return "redirect:/";
    }}