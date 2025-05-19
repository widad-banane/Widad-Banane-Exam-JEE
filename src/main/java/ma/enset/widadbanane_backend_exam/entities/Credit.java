package ma.enset.widadbanane_backend_exam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CREDIT", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateDemande;

    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer dureeRemboursement;
    private Double tauxInteret;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<Remboursement> remboursements;

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

    public Date getDateAcceptation() {
        return dateAcceptation;
    }

    public void setDateAcceptation(Date dateAcceptation) {
        this.dateAcceptation = dateAcceptation;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Remboursement> getRemboursements() {
        return remboursements;
    }

    public void setRemboursements(List<Remboursement> remboursements) {
        this.remboursements = remboursements;
    }
}
