package dev.anto.gestore.catalogo.service;

import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.repository.OrderRepository;
import dev.anto.gestore.catalogo.security.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<OrderUserDto> findAllByUser() {
        var email = authService.getEmail();

        return orderRepository.findAllByUtente(email).stream()
                .map(entity -> modelMapper.map(entity, OrderUserDto.class))
                .toList();
    }
}
