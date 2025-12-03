package de.ait.training.controller;


import de.ait.training.enums.Role;
import de.ait.training.model.User;
import de.ait.training.repository.SDUserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private final SDUserRepository userRepository;

    public AdminController(SDUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/admin/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }}
