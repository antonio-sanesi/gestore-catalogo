package dev.anto.gestore.catalogo.controller;

import dev.anto.gestore.catalogo.dto.LoginDto;
import dev.anto.gestore.catalogo.security.JwtService;
import dev.anto.gestore.catalogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("login")
    public String login(
            @RequestBody LoginDto loginData
    ){
        return jwtService.login(loginData.getEmail(), loginData.getPassword());
    }

}
