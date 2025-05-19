package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.ClientDTO;
import ma.enset.widadbanane_backend_exam.entities.Client;
import ma.enset.widadbanane_backend_exam.mappers.ClientMapper;
import ma.enset.widadbanane_backend_exam.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTO(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.fromClient(savedClient);
    }

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return clientMapper.fromClient(client);
    }

    @Override
    public List<ClientDTO> listClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Client client = clientMapper.fromClientDTO(clientDTO);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.fromClient(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> searchClients(String keyword) {
        List<Client> clients = clientRepository.findByNomContainsIgnoreCase(keyword);
        return clients.stream()
                .map(clientMapper::fromClient)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO findByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new RuntimeException("Client not found with email: " + email);
        }
        return clientMapper.fromClient(client);
    }
}