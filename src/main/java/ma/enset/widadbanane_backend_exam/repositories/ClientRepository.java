package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainsIgnoreCase(String nom);
    Client findByEmail(String email);
    boolean existsByEmail(String email);
}