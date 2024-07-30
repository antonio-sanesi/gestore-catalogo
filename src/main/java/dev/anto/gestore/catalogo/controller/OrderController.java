package dev.anto.gestore.catalogo.controller;

import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.entity.Order;
import dev.anto.gestore.catalogo.service.interfaces.OrderService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RolesAllowed("CUSTOMER")
    @PostMapping("")
    public OrderUserDto addOrder(
            @Valid @RequestBody OrderUserDto theOrder
    ) {
        theOrder.setId(null);
        return orderService.save(theOrder);
    }

    @RolesAllowed("CUSTOMER")
    @PutMapping("/{orderId}")
    public OrderUserDto updateOrder(
            @PathVariable Integer orderId,
            @Valid @RequestBody OrderUserDto theOrder
    ) {
        theOrder.setId(orderId);
        System.out.println(theOrder.getId());
        return orderService.save(theOrder);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{orderId}")
    public String deleteProduct(@PathVariable int orderId) {
        orderService.deleteById(orderId);
        return "Deleted Product - " + orderId;
    }
}
