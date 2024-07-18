package dev.anto.gestore.catalogo.controller;

import dev.anto.gestore.catalogo.dto.LoginDto;
import dev.anto.gestore.catalogo.security.JwtService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("login")
    public String login(
            @RequestBody LoginDto loginData
    ){
        return jwtService.login(loginData.getEmail(), loginData.getPassword());
    }

}
