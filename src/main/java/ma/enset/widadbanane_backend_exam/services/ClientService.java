package ma.enset.widadbanane_backend_exam.services;

import ma.enset.widadbanane_backend_exam.dtos.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClient(Long id);
    List<ClientDTO> listClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    List<ClientDTO> searchClients(String keyword);
    ClientDTO findByEmail(String email);
}