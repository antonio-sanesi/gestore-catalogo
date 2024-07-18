package dev.anto.gestore.catalogo.service.Implementation;

import dev.anto.gestore.catalogo.dto.OrderUserDto;
import dev.anto.gestore.catalogo.entity.Order;
import dev.anto.gestore.catalogo.repository.OrderRepository;
import dev.anto.gestore.catalogo.repository.UserRepository;
import dev.anto.gestore.catalogo.security.AuthService;
import dev.anto.gestore.catalogo.service.interfaces.OrderService;
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

    @Override
    public List<OrderUserDto> findAllByUser() {
        var email = authService.getEmail();

        return orderRepository.findAllByUtente(email).stream()
                .map(entity -> modelMapper.map(entity, OrderUserDto.class))
                .toList();
    }

    @Override
    public Order save(Order theOrder) {
        //calcolo il prezzo finale moltiplicando il prezzo del singolo oggetto per la quantità
        var quantity = BigDecimal.valueOf(theOrder.getQuantity());
        var finalPrice = theOrder.getPrice().multiply(quantity);
        theOrder.setPrice(finalPrice);
        var email = authService.getEmail();
        var utente = userRepository.findById(email).orElseThrow();
        theOrder.setUtente(utente);

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
