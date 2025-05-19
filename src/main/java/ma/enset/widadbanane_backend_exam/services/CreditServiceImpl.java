package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.CreditDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditImmobilierDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditPersonnelDTO;
import ma.enset.widadbanane_backend_exam.dtos.CreditProfessionnelDTO;
import ma.enset.widadbanane_backend_exam.entities.*;
import ma.enset.widadbanane_backend_exam.enums.StatutCredit;
import ma.enset.widadbanane_backend_exam.mappers.CreditMapper;
import ma.enset.widadbanane_backend_exam.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final ClientRepository clientRepository;
    private final CreditMapper creditMapper;

    public CreditServiceImpl(CreditRepository creditRepository,
                             CreditPersonnelRepository creditPersonnelRepository,
                             CreditImmobilierRepository creditImmobilierRepository,
                             CreditProfessionnelRepository creditProfessionnelRepository,
                             ClientRepository clientRepository,
                             CreditMapper creditMapper) {
        this.creditRepository = creditRepository;
        this.creditPersonnelRepository = creditPersonnelRepository;
        this.creditImmobilierRepository = creditImmobilierRepository;
        this.creditProfessionnelRepository = creditProfessionnelRepository;
        this.clientRepository = clientRepository;
        this.creditMapper = creditMapper;
    }

    @Override
    public CreditDTO getCredit(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
        return creditMapper.fromCredit(credit);
    }

    @Override
    public List<CreditDTO> listCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByClient(Long clientId) {
        List<Credit> credits = creditRepository.findByClientId(clientId);
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(StatutCredit statut) {
        List<Credit> credits = creditRepository.findByStatut(statut);
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public CreditPersonnelDTO savePersonnelCredit(CreditPersonnelDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + creditDTO.getClientId()));

        CreditPersonnel creditPersonnel = new CreditPersonnel();
        creditPersonnel.setDateDemande(new Date());
        creditPersonnel.setStatut(creditDTO.getStatut());
        creditPersonnel.setMontant(creditDTO.getMontant());
        creditPersonnel.setDureeRemboursement(creditDTO.getDureeRemboursement());
        creditPersonnel.setTauxInteret(creditDTO.getTauxInteret());
        creditPersonnel.setMotif(creditDTO.getMotif());
        creditPersonnel.setClient(client);

        CreditPersonnel savedCredit = creditPersonnelRepository.save(creditPersonnel);
        return (CreditPersonnelDTO) creditMapper.fromCredit(savedCredit);
    }

    @Override
    public CreditImmobilierDTO saveImmobilierCredit(CreditImmobilierDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + creditDTO.getClientId()));

        CreditImmobilier creditImmobilier = new CreditImmobilier();
        creditImmobilier.setDateDemande(new Date());
        creditImmobilier.setStatut(creditDTO.getStatut());
        creditImmobilier.setMontant(creditDTO.getMontant());
        creditImmobilier.setDureeRemboursement(creditDTO.getDureeRemboursement());
        creditImmobilier.setTauxInteret(creditDTO.getTauxInteret());
        creditImmobilier.setTypeBien(creditDTO.getTypeBien());
        creditImmobilier.setClient(client);

        CreditImmobilier savedCredit = creditImmobilierRepository.save(creditImmobilier);
        return (CreditImmobilierDTO) creditMapper.fromCredit(savedCredit);
    }

    @Override
    public CreditProfessionnelDTO saveProfessionnelCredit(CreditProfessionnelDTO creditDTO) {
        Client client = clientRepository.findById(creditDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + creditDTO.getClientId()));

        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
        creditProfessionnel.setDateDemande(new Date());
        creditProfessionnel.setStatut(creditDTO.getStatut());
        creditProfessionnel.setMontant(creditDTO.getMontant());
        creditProfessionnel.setDureeRemboursement(creditDTO.getDureeRemboursement());
        creditProfessionnel.setTauxInteret(creditDTO.getTauxInteret());
        creditProfessionnel.setMotif(creditDTO.getMotif());
        creditProfessionnel.setRaisonSociale(creditDTO.getRaisonSociale());
        creditProfessionnel.setClient(client);

        CreditProfessionnel savedCredit = creditProfessionnelRepository.save(creditProfessionnel);
        return (CreditProfessionnelDTO) creditMapper.fromCredit(savedCredit);
    }

    @Override
    public CreditDTO updateCreditStatus(Long creditId, StatutCredit newStatus) {
        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + creditId));

        credit.setStatut(newStatus);
        if (newStatus == StatutCredit.ACCEPTE) {
            credit.setDateAcceptation(new Date());
        }

        Credit updatedCredit = creditRepository.save(credit);
        return creditMapper.fromCredit(updatedCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}