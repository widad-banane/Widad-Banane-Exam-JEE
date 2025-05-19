package ma.enset.widadbanane_backend_exam;

import ma.enset.widadbanane_backend_exam.entities.*;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;
import ma.enset.widadbanane_backend_exam.enums.TypeRemboursement;
import ma.enset.widadbanane_backend_exam.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class WidadBananeBackendExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(WidadBananeBackendExamApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ClientRepository clientRepository,
                            CreditImmobilierRepository creditImmobilierRepository,
                            CreditPersonnelRepository creditPersonnelRepository,
                            CreditProfessionnelRepository creditProfessionnelRepository,
                            RemboursementRepository remboursementRepository) {
        return args -> {
            // Création d'un client
            Client client = new Client();
            client.setNom("Widad");
            client.setEmail("widad@example.com");
            clientRepository.save(client);

            Client client2 = new Client();
            client2.setNom("Banane");
            client2.setEmail("banane@example.com");
            clientRepository.save(client2);

            // Crédit Immobilier
            CreditImmobilier immobilier = new CreditImmobilier();
            immobilier.setDateDemande(new Date());
            immobilier.setStatut(StatutCredit.EN_COURS);
            immobilier.setMontant(500000.0);
            immobilier.setDureeRemboursement(240);
            immobilier.setTauxInteret(3.5);
            immobilier.setTypeBien(TypeBienImmobilier.APPARTEMENT);
            immobilier.setClient(client);
            creditImmobilierRepository.save(immobilier);

            // Crédit Personnel
            CreditPersonnel personnel = new CreditPersonnel();
            personnel.setDateDemande(new Date());
            personnel.setStatut(StatutCredit.ACCEPTE);
            personnel.setDateAcceptation(new Date());
            personnel.setMontant(100000.0);
            personnel.setDureeRemboursement(60);
            personnel.setTauxInteret(4.0);
            personnel.setMotif("Achat de voiture");
            personnel.setClient(client);
            creditPersonnelRepository.save(personnel);

            // Crédit Professionnel
            CreditProfessionnel professionnel = new CreditProfessionnel();
            professionnel.setDateDemande(new Date());
            professionnel.setStatut(StatutCredit.REJETE);
            professionnel.setMontant(300000.0);
            professionnel.setDureeRemboursement(120);
            professionnel.setTauxInteret(5.0);
            professionnel.setMotif("Extension de l'entreprise");
            professionnel.setRaisonSociale("BananeTech SARL");
            professionnel.setClient(client2);
            creditProfessionnelRepository.save(professionnel);

            // Remboursements
            Remboursement remboursement1 = new Remboursement();
            remboursement1.setDate(LocalDate.now());
            remboursement1.setMontant(2000.0);
            remboursement1.setType(TypeRemboursement.MENSUALITE);
            remboursement1.setCredit(personnel);
            remboursementRepository.save(remboursement1);

            Remboursement remboursement2 = new Remboursement();
            remboursement2.setDate(LocalDate.now().minusMonths(1));
            remboursement2.setMontant(2000.0);
            remboursement2.setType(TypeRemboursement.MENSUALITE);
            remboursement2.setCredit(personnel);
            remboursementRepository.save(remboursement2);

            Remboursement remboursement3 = new Remboursement();
            remboursement3.setDate(LocalDate.now());
            remboursement3.setMontant(10000.0);
            remboursement3.setType(TypeRemboursement.REMBOURSEMENT_ANTICIPE);
            remboursement3.setCredit(immobilier);
            remboursementRepository.save(remboursement3);
        };
    }
}