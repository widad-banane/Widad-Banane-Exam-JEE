package ma.enset.widadbanane_backend_exam.mappers;

import ma.enset.widadbanane_backend_exam.dtos.ClientDTO;
import ma.enset.widadbanane_backend_exam.entities.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientMapper {
    private final CreditMapper creditMapper;

    public ClientMapper(CreditMapper creditMapper) {
        this.creditMapper = creditMapper;
    }

    public ClientDTO fromClient(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        BeanUtils.copyProperties(client, clientDTO);
        try {
            if (client.getCredits() != null) {
                clientDTO.setCredits(client.getCredits().stream()
                        .map(creditMapper::fromCredit)
                        .toList());
            }
        } catch (Exception e) {
            // If getCredits() method is not available, set credits to null
            clientDTO.setCredits(null);
        }
        return clientDTO;
    }

    public Client fromClientDTO(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }
}