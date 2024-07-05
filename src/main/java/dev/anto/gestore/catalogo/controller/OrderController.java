package dev.anto.gestore.catalogo.controller;

import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public List<OrderUserDto> getAllOrdersByUser(){
        return orderService.findAllByUser();
    }

}
