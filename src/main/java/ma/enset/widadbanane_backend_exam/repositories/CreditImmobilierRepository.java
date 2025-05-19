package ma.enset.widadbanane_backend_exam.repositories;

import ma.enset.widadbanane_backend_exam.entities.CreditImmobilier;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(TypeBienImmobilier typeBien);
    List<CreditImmobilier> findByTypeBienAndStatut(TypeBienImmobilier typeBien, StatutCredit statut);
    List<CreditImmobilier> findByClientIdAndTypeBien(Long clientId, TypeBienImmobilier typeBien);
}