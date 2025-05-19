package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.CreditPersonnel;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByMotifContainsIgnoreCase(String motif);
    List<CreditPersonnel> findByClientIdAndStatut(Long clientId, StatutCredit statut);
    List<CreditPersonnel> findByMontantLessThan(Double montant);
}