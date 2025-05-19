package ma.enset.widadbanane_backend_exam.web;

import ma.enset.widadbanane_backend_exam.entities.Client;
import ma.enset.widadbanane_backend_exam.entities.CreditPersonnel;
import ma.enset.widadbanane_backend_exam.repositories.ClientRepository;
import ma.enset.widadbanane_backend_exam.repositories.CreditPersonnelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/credits/personnel")
public class CreditPersonnelRestController {
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final ClientRepository clientRepository;

    public CreditPersonnelRestController(CreditPersonnelRepository creditPersonnelRepository,
                                         ClientRepository clientRepository) {
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<CreditPersonnel> getAllPersonnelCredits() {
        return creditPersonnelRepository.findAll();
    }

    @PostMapping
    public CreditPersonnel createPersonnelCredit(@RequestBody CreditPersonnel creditPersonnel,
                                                 @RequestParam Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientId));
        creditPersonnel.setClient(client);
        creditPersonnel.setDateDemande(new Date());
        return creditPersonnelRepository.save(creditPersonnel);
    }
}