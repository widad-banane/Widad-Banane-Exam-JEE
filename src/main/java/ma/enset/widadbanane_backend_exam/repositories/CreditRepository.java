package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.Credit;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatut(StatutCredit statut);
    List<Credit> findByMontantGreaterThan(Double montant);
    List<Credit> findByDateDemandeAfter(Date date);

    @Query("SELECT c FROM Credit c WHERE c.dureeRemboursement > :duree")
    List<Credit> findCreditsWithDureeGreaterThan(@Param("duree") Integer duree);

    @Query("SELECT c FROM Credit c WHERE c.tauxInteret BETWEEN :min AND :max")
    List<Credit> findCreditsByTauxInteretRange(@Param("min") Double min, @Param("max") Double max);
}