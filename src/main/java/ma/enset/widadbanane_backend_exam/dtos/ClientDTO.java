package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import java.util.List;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
    private List<CreditDTO> credits;
    
    // Manually add setter method
    public void setCredits(List<CreditDTO> credits) {
        this.credits = credits;
    }
}
