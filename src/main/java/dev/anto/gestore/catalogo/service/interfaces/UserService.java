package dev.anto.gestore.catalogo.service.interfaces;

import dev.anto.gestore.catalogo.dto.SignUpDto;
import dev.anto.gestore.catalogo.dto.UserDto;

public interface UserService {
    void signup(SignUpDto userData);

    UserDto getUser();

    UserDto editUser(SignUpDto userData);
}
