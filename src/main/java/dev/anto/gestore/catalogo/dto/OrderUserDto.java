package dev.anto.gestore.catalogo.dto;

import dev.anto.gestore.catalogo.entity.Product;
import lombok.*;

import java.math.BigDecimal;


@Data
public class OrderUserDto {
    private Integer id;
//    private UserDto utente;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
