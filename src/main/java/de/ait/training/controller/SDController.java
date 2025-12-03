package de.ait.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/hours")
public class SDController {



    @GetMapping("/my")
    public List<String> checkHours (@AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        return List.of("список дней и часов пользователя " +  username);
    }

    @PostMapping("/start")
    public String startWork(@AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        return HttpStatus.OK.toString();
    }

    @PostMapping("/end")
    public String endWork(@AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        return HttpStatus.OK.toString();
    }
}
