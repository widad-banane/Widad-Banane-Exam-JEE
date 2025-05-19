package ma.enset.widadbanane_backend_exam.web;

import ma.enset.widadbanane_backend_exam.entities.Client;
import ma.enset.widadbanane_backend_exam.entities.CreditImmobilier;
import ma.enset.widadbanane_backend_exam.repositories.ClientRepository;
import ma.enset.widadbanane_backend_exam.repositories.CreditImmobilierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/credits/immobilier")
public class CreditImmobilierRestController {
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final ClientRepository clientRepository;

    public CreditImmobilierRestController(CreditImmobilierRepository creditImmobilierRepository,
                                          ClientRepository clientRepository) {
        this.creditImmobilierRepository = creditImmobilierRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<CreditImmobilier> getAllImmobilierCredits() {
        return creditImmobilierRepository.findAll();
    }

    @PostMapping
    public CreditImmobilier createImmobilierCredit(@RequestBody CreditImmobilier creditImmobilier,
                                                   @RequestParam Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        creditImmobilier.setClient(client);
        creditImmobilier.setDateDemande(new Date());
        return creditImmobilierRepository.save(creditImmobilier);
    }
}