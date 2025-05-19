package ma.enset.widadbanane_backend_exam.web;

import ma.enset.widadbanane_backend_exam.entities.Client;
import ma.enset.widadbanane_backend_exam.entities.CreditProfessionnel;
import ma.enset.widadbanane_backend_exam.repositories.ClientRepository;
import ma.enset.widadbanane_backend_exam.repositories.CreditProfessionnelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/credits/professionnel")
public class CreditProfessionnelRestController {
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final ClientRepository clientRepository;

    public CreditProfessionnelRestController(CreditProfessionnelRepository creditProfessionnelRepository,
                                             ClientRepository clientRepository) {
        this.creditProfessionnelRepository = creditProfessionnelRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<CreditProfessionnel> getAllProfessionnelCredits() {
        return creditProfessionnelRepository.findAll();
    }

    @PostMapping
    public CreditProfessionnel createProfessionnelCredit(@RequestBody CreditProfessionnel creditProfessionnel,
                                                         @RequestParam Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        creditProfessionnel.setClient(client);
        creditProfessionnel.setDateDemande(new Date());
        return creditProfessionnelRepository.save(creditProfessionnel);
    }
}