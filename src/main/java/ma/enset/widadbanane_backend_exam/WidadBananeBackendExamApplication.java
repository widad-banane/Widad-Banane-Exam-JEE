package ma.enset.widadbanane_backend_exam;

import ma.enset.widadbanane_backend_exam.entities.*;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;
import ma.enset.widadbanane_backend_exam.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                            CreditProfessionnelRepository creditProfessionnelRepository) {
        return args -> {
            // Création d'un client (version corrigée sans builder)
            Client client = new Client();
            client.setNom("Widad");
            client.setEmail("widad@example.com");
            clientRepository.save(client);

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
            professionnel.setClient(client);
            creditProfessionnelRepository.save(professionnel);
        };
    }
}