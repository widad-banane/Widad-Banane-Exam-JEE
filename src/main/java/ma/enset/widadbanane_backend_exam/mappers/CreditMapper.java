package ma.enset.widadbanane_backend_exam.mappers;

import ma.enset.widadbanane_backend_exam.dtos.*;
import ma.enset.widadbanane_backend_exam.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CreditMapper {

    public CreditDTO fromCredit(Credit credit) {
        CreditDTO creditDTO;

        if (credit instanceof CreditImmobilier) {
            CreditImmobilierDTO creditImmobilierDTO = new CreditImmobilierDTO();
            CreditImmobilier creditImmobilier = (CreditImmobilier) credit;
            // Access the field directly if getter is not available
            creditImmobilierDTO.setTypeBien(creditImmobilier.getTypeBien());
            creditDTO = creditImmobilierDTO;
        } else if (credit instanceof CreditPersonnel) {
            CreditPersonnelDTO creditPersonnelDTO = new CreditPersonnelDTO();
            CreditPersonnel creditPersonnel = (CreditPersonnel) credit;
            creditPersonnelDTO.setMotif(creditPersonnel.getMotif());
            creditDTO = creditPersonnelDTO;
        } else if (credit instanceof CreditProfessionnel) {
            CreditProfessionnelDTO creditProfessionnelDTO = new CreditProfessionnelDTO();
            CreditProfessionnel creditProfessionnel = (CreditProfessionnel) credit;
            creditProfessionnelDTO.setMotif(creditProfessionnel.getMotif());
            creditProfessionnelDTO.setRaisonSociale(creditProfessionnel.getRaisonSociale());
            creditDTO = creditProfessionnelDTO;
        } else {
            creditDTO = new CreditDTO();
        }

        BeanUtils.copyProperties(credit, creditDTO);
        if (credit.getClient() != null) {
            creditDTO.setClientId(credit.getClient().getId());
        }

        return creditDTO;
    }

    public Credit fromCreditDTO(CreditDTO creditDTO) {
        Credit credit;

        if (creditDTO instanceof CreditImmobilierDTO) {
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            CreditImmobilierDTO creditImmobilierDTO = (CreditImmobilierDTO) creditDTO;
            creditImmobilier.setTypeBien(creditImmobilierDTO.getTypeBien());
            credit = creditImmobilier;
        } else if (creditDTO instanceof CreditPersonnelDTO) {
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            CreditPersonnelDTO creditPersonnelDTO = (CreditPersonnelDTO) creditDTO;
            creditPersonnel.setMotif(creditPersonnelDTO.getMotif());
            credit = creditPersonnel;
        } else if (creditDTO instanceof CreditProfessionnelDTO) {
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            CreditProfessionnelDTO creditProfessionnelDTO = (CreditProfessionnelDTO) creditDTO;
            creditProfessionnel.setMotif(creditProfessionnelDTO.getMotif());
            creditProfessionnel.setRaisonSociale(creditProfessionnelDTO.getRaisonSociale());
            credit = creditProfessionnel;
        } else {
            throw new IllegalArgumentException("Unknown credit type");
        }

        return credit;
    }
}
