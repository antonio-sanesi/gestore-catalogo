package dev.anto.gestore.catalogo.service;

import dev.anto.gestore.catalogo.dto.OrderUserDto;

import java.util.List;

public interface OrderService {
    List<OrderUserDto> findAllByUser();
}
