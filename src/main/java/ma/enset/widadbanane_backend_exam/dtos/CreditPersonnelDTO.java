package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditPersonnelDTO extends CreditDTO {
    private String motif;
}