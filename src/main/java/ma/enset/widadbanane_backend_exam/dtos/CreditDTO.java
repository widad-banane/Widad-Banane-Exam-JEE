package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import java.util.Date;

@Data
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private StatutCredit statut;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;
    private Long clientId;
}