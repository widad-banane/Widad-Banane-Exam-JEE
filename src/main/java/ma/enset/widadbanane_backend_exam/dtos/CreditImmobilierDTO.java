package ma.enset.widadbanane_backend_exam.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBienImmobilier typeBien;

    // Explicit getter and setter for typeBien
    public TypeBienImmobilier getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(TypeBienImmobilier typeBien) {
        this.typeBien = typeBien;
    }
}