package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.CreditProfessionnel;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByRaisonSocialeContainsIgnoreCase(String raisonSociale);
    List<CreditProfessionnel> findByMotifContainsIgnoreCase(String motif);
    List<CreditProfessionnel> findByClientIdAndStatut(Long clientId, StatutCredit statut);
}