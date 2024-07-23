package dev.anto.gestore.catalogo.service.impl;

import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.entity.Order;
import dev.anto.gestore.catalogo.repository.OrderRepository;
import dev.anto.gestore.catalogo.repository.ProductRepository;
import dev.anto.gestore.catalogo.repository.UserRepository;
import dev.anto.gestore.catalogo.security.AuthService;
import dev.anto.gestore.catalogo.service.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderUserDto> findAllByUser() {
        var email = authService.getEmail();

        return orderRepository.findAllByUtente(email).stream()
                .map(entity -> modelMapper.map(entity, OrderUserDto.class))
                .toList();
    }

    @Override
    public OrderUserDto save(@Valid OrderUserDto theOrderDto) {
        var theOrder = modelMapper.map(theOrderDto, Order.class);

        //ottengo il prodotto originale dal DB per leggere il prezzo
        var prodotto = productRepository.findById(
                theOrder.getProduct().getId()
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prodotto non valido"));

        var pricePerUnit = prodotto.getPrice();

        //calcolo il prezzo finale moltiplicando il prezzo del singolo oggetto per la quantità
        var quantity = BigDecimal.valueOf(theOrder.getQuantity());
        var finalPrice = pricePerUnit.multiply(quantity);

        //modifico l'ordine inserendo il prezzo calcolato
        theOrder.setPrice(finalPrice);

        //ottengo l'utente che sta facendo l'ordine
        var mail = authService.getEmail();
        var utenteOrdinante = userRepository.findById(mail).orElseThrow();

        //imposto l'utente sull'ordine
        theOrder.setUtente(utenteOrdinante);

        //salvo l'ordine
        var saved = orderRepository.save(theOrder);

        return modelMapper.map(saved, OrderUserDto.class);
    }

    @Override
    public void deleteById(int theId) {
        var daCancellare = this.findById(theId);
        orderRepository.delete(daCancellare);
    }

    @Override
    public Order findById (int theId) {
        return orderRepository.findById(theId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "nessun ordine presente"));
    }
}
