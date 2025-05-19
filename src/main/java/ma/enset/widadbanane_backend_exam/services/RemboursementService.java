package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.RemboursementDTO;
import ma.enset.widadbanane_backend_exam.enums.TypeRemboursement;

import java.time.LocalDate;
import java.util.List;

public interface RemboursementService {
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO getRemboursement(Long id);
    List<RemboursementDTO> listRemboursements();
    List<RemboursementDTO> getRemboursementsByCredit(Long creditId);
    List<RemboursementDTO> getRemboursementsByClient(Long clientId);
    List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type);
    List<RemboursementDTO> getRemboursementsAfterDate(LocalDate date);
    Double getTotalRemboursementForCredit(Long creditId);
    void deleteRemboursement(Long id);
}