package dev.anto.gestore.catalogo.service.interfaces;

import dev.anto.gestore.catalogo.dto.OrderAdminDto;
import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderUserDto> findAllByUser();



    Order findById(int theId);


    void deleteById(int theId);



    OrderUserDto save(OrderUserDto theOrder);

    List<OrderAdminDto> findAll();
}
