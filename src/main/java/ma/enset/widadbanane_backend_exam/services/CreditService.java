package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.CreditDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditImmobilierDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditPersonnelDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditProfessionnelDTO;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;

import java.util.List;

public interface CreditService {
    CreditDTO getCredit(Long id);
    List<CreditDTO> listCredits();
    List<CreditDTO> getCreditsByClient(Long clientId);
    List<CreditDTO> getCreditsByStatus(StatutCredit statut);

    CreditPersonnelDTO savePersonnelCredit(CreditPersonnelDTO creditDTO);
    CreditImmobilierDTO saveImmobilierCredit(CreditImmobilierDTO creditDTO);
    CreditProfessionnelDTO saveProfessionnelCredit(CreditProfessionnelDTO creditDTO);

    CreditDTO updateCreditStatus(Long creditId, StatutCredit newStatus);
    void deleteCredit(Long id);
}