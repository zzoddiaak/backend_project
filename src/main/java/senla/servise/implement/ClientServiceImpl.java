package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;
import senla.mapper.ClientMapper;
import senla.entities.Client;
import senla.repository.iface.ClientRepository;
import senla.servise.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<ClientShortInfoDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::convertToShortDto)
                .toList();
    }

    @Override
    public ClientShortInfoDTO findById(long uuid) {
        Client client = clientRepository.findById(uuid);
        return client != null ? ClientMapper.convertToShortDto(client) : null;
    }

    @Override
    public boolean save(ClientDTOToEntity object) {
        Client client = ClientMapper.createClientDto(object);
        clientRepository.save(client);
        return client.getId() != null;
    }

    @Override
    public void deleteById(long uuid){
        clientRepository.deleteById(uuid);
    }
}

