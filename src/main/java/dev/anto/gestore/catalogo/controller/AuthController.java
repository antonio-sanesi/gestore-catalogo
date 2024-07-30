package dev.anto.gestore.catalogo.controller;

import dev.anto.gestore.catalogo.dto.LoginDto;
import dev.anto.gestore.catalogo.dto.SignUpDto;
import dev.anto.gestore.catalogo.dto.UserDto;
import dev.anto.gestore.catalogo.security.AuthService;
import dev.anto.gestore.catalogo.security.JwtService;
import dev.anto.gestore.catalogo.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("login")
    public String login(
            @RequestBody LoginDto loginData
    ){
        return jwtService.login(loginData.getEmail(), loginData.getPassword());
    }

    @PostMapping("signup")
    public String signup(
            @Valid @RequestBody SignUpDto userData
    ) {
        userService.signup(userData);
        return jwtService.login(userData.getEmail(), userData.getPassword());
    }

    @GetMapping("authorities")
    public List<String> authorities(){
        return authService.getAuthorities();
    }

    @GetMapping("user")
    public UserDto getUser(){
        return userService.getUser();
    }

    @PutMapping("user")
    public UserDto editUser(
            @Valid @RequestBody SignUpDto userData
    ){
        return userService.editUser(userData);
    }

}
