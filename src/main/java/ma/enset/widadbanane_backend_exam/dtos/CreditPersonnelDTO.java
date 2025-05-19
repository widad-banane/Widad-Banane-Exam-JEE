package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditPersonnelDTO extends CreditDTO {
    private String motif;

    // Explicit getter and setter for motif
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
}