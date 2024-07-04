package dev.anto.gestore.catalogo.repository;

import dev.anto.gestore.catalogo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
