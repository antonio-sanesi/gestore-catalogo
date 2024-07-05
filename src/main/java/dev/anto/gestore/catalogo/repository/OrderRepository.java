package dev.anto.gestore.catalogo.repository;

import dev.anto.gestore.catalogo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.utente.email = ?1")
    List<Order> findAllByUtente(String email);
}
