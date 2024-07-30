package dev.anto.gestore.catalogo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name="product")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    @NotNull(message = "Il nome non dev'essere vuoto")
    @Size(min = 3, max = 100, message = "Il nome deve avere tra i 3 e i 100 caratteri")
    private String name;

    @Column(name="description")
    @NotNull(message = "La descrizione non dev'essere vuota")
    @Size(min = 3, max = 200, message = "La descrizione deve avere tra i 3 e i 200 caratteri")
    private String description;

    @Column(name="price")
    @NotNull(message = "Il prezzo non dev'essere vuoto")
    @Min(value = 0, message = "Il prezzo dev'essere maggiore di 0")
    private BigDecimal price;

    @Column(name = "visible")
    private Boolean visible = true;
}
