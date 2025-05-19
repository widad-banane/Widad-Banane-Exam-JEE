package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {   // Additional methods specific to CreditPersonnel can be defined here
}
