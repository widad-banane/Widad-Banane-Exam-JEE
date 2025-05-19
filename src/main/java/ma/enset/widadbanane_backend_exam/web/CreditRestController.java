package ma.enset.widadbanane_backend_exam.web;

import ma.enset.widadbanane_backend_exam.dtos.CreditDTO;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import ma.enset.widadbanane_backend_exam.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
public class CreditRestController {
    private final CreditService creditService;

    public CreditRestController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditService.listCredits();
    }

    @GetMapping("/{id}")
    public CreditDTO getCreditById(@PathVariable Long id) {
        return creditService.getCredit(id);
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getCreditsByClientId(@PathVariable Long clientId) {
        return creditService.getCreditsByClient(clientId);
    }

    @GetMapping("/status/{status}")
    public List<CreditDTO> getCreditsByStatus(@PathVariable StatutCredit status) {
        return creditService.getCreditsByStatus(status);
    }

    @PatchMapping("/{id}/status")
    public CreditDTO updateCreditStatus(@PathVariable Long id, @RequestParam StatutCredit status) {
        return creditService.updateCreditStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
}