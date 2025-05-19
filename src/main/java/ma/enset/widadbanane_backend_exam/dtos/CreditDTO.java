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

    // Explicit getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public StatutCredit getStatut() {
        return statut;
    }

    public void setStatut(StatutCredit statut) {
        this.statut = statut;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getDureeRemboursement() {
        return dureeRemboursement;
    }

    public void setDureeRemboursement(Integer dureeRemboursement) {
        this.dureeRemboursement = dureeRemboursement;
    }

    public Double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}