package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.RemboursementDTO;
import ma.enset.widadbanane_backend_exam.entities.Credit;
import ma.enset.widadbanane_backend_exam.entities.Remboursement;
import ma.enset.widadbanane_backend_exam.enums.TypeRemboursement;
import ma.enset.widadbanane_backend_exam.mappers.RemboursementMapper;
import ma.enset.widadbanane_backend_exam.repositories.CreditRepository;
import ma.enset.widadbanane_backend_exam.repositories.RemboursementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RemboursementServiceImpl implements RemboursementService {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    public RemboursementServiceImpl(RemboursementRepository remboursementRepository,
                                    CreditRepository creditRepository,
                                    RemboursementMapper remboursementMapper) {
        this.remboursementRepository = remboursementRepository;
        this.creditRepository = creditRepository;
        this.remboursementMapper = remboursementMapper;
    }

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Credit credit = creditRepository.findById(remboursementDTO.getCreditId())
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + remboursementDTO.getCreditId()));

        Remboursement remboursement = remboursementMapper.fromRemboursementDTO(remboursementDTO);
        remboursement.setCredit(credit);

        if (remboursement.getDate() == null) {
            remboursement.setDate(LocalDate.now());
        }

        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.fromRemboursement(savedRemboursement);
    }

    @Override
    public RemboursementDTO getRemboursement(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remboursement not found with id: " + id));
        return remboursementMapper.fromRemboursement(remboursement);
    }

    @Override
    public List<RemboursementDTO> listRemboursements() {
        List<Remboursement> remboursements = remboursementRepository.findAll();
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCredit(Long creditId) {
        List<Remboursement> remboursements = remboursementRepository.findByCreditId(creditId);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByClient(Long clientId) {
        List<Remboursement> remboursements = remboursementRepository.findRemboursementsByClientId(clientId);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type) {
        List<Remboursement> remboursements = remboursementRepository.findByType(type);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsAfterDate(LocalDate date) {
        List<Remboursement> remboursements = remboursementRepository.findByDateAfter(date);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalRemboursementForCredit(Long creditId) {
        return remboursementRepository.getTotalRemboursementByCreditId(creditId);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }
}