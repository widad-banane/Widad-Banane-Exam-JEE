package ma.enset.widadbanane_backend_exam.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ma.enset.widadbanane_backend_exam.enums.TypeBienImmobilier;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("IMMOBILIER")
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBienImmobilier typeBien;

    // Explicit getter and setter
    public TypeBienImmobilier getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(TypeBienImmobilier typeBien) {
        this.typeBien = typeBien;
    }
}
