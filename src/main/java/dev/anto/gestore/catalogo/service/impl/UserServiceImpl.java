package dev.anto.gestore.catalogo.service.impl;

import dev.anto.gestore.catalogo.dto.SignUpDto;
import dev.anto.gestore.catalogo.dto.UserDto;
import dev.anto.gestore.catalogo.entity.User;
import dev.anto.gestore.catalogo.repository.UserRepository;
import dev.anto.gestore.catalogo.security.AuthService;
import dev.anto.gestore.catalogo.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;

    @Override
    public void signup(SignUpDto userData) {
        userRepository.findById(userData.getEmail()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email già registrata");
        });

        var salt = BCrypt.gensalt();
        var hash = BCrypt.hashpw(userData.getPassword(), salt);

        var user = modelMapper.map(userData, User.class);
        user.setPassword(hash);

        userRepository.save(user);
    }

    @Override
    public UserDto getUser() {
        var mail = authService.getEmail();

        var user = userRepository.findById(mail);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto editUser(SignUpDto userData) {
        var mail = authService.getEmail();
        if(!userData.getEmail().equals(mail)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non puoi cambiare email");
        }

        var user = userRepository.findById(mail).orElseThrow();

        user.setName(userData.getName());
        user.setSurname(userData.getSurname());

        if(userData.getPassword() != null){
            var salt = BCrypt.gensalt();
            var hash = BCrypt.hashpw(userData.getPassword(), salt);

            user.setPassword(hash);
        }

        var saved = userRepository.save(user);

        return modelMapper.map(saved, UserDto.class);
    }

}
