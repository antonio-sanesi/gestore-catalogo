package dev.anto.gestore.catalogo.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    public String getEmail(){
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore critico di autenticazione");
        }
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}
