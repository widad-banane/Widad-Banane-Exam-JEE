package ma.enset.widadbanane_backend_exam.web;

import ma.enset.widadbanane_backend_exam.entities.Credit;
import ma.enset.widadbanane_backend_exam.entities.Remboursement;
import ma.enset.widadbanane_backend_exam.repositories.CreditRepository;
import ma.enset.widadbanane_backend_exam.repositories.RemboursementRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementRestController {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;

    public RemboursementRestController(RemboursementRepository remboursementRepository,
                                       CreditRepository creditRepository) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
    }

    @GetMapping
    public List<Remboursement> getAllRemboursements() {
        return remboursementRepository.findAll();
    }

    @PostMapping
    public Remboursement createRemboursement(@RequestBody Remboursement remboursement,
                                             @RequestParam Long creditId) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + creditId));
        remboursement.setCredit(credit);
        if (remboursement.getDate() == null) {
            remboursement.setDate(LocalDate.now());
        }
        return remboursementRepository.save(remboursement);
    }
}