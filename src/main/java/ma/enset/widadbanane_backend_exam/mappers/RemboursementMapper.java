package ma.enset.widadbanane_backend_exam.mappers;

import ma.enset.widadbanane_backend_exam.dtos.RemboursementDTO;
import ma.enset.widadbanane_backend_exam.entities.Remboursement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RemboursementMapper {

    public RemboursementDTO fromRemboursement(Remboursement remboursement) {
        RemboursementDTO remboursementDTO = new RemboursementDTO();
        BeanUtils.copyProperties(remboursement, remboursementDTO);
        if (remboursement.getCredit() != null) {
            remboursementDTO.setCreditId(remboursement.getCredit().getId());
        }
        return remboursementDTO;
    }

    public Remboursement fromRemboursementDTO(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = new Remboursement();
        BeanUtils.copyProperties(remboursementDTO, remboursement, "creditId");
        return remboursement;
    }
}