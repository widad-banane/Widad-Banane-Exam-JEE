package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBienImmobilier typeBien;
}