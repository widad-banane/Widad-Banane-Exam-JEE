package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.Remboursement;
import ma.enset.widadbanane_backend_exam.enums.TypeRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCreditId(Long creditId);
    List<Remboursement> findByType(TypeRemboursement type);
    List<Remboursement> findByDateAfter(LocalDate date);
    List<Remboursement> findByMontantGreaterThan(Double montant);

    @Query("SELECT SUM(r.montant) FROM Remboursement r WHERE r.credit.id = :creditId")
    Double getTotalRemboursementByCreditId(@Param("creditId") Long creditId);

    @Query("SELECT r FROM Remboursement r WHERE r.credit.client.id = :clientId")
    List<Remboursement> findRemboursementsByClientId(@Param("clientId") Long clientId);
}