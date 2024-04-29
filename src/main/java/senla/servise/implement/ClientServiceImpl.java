package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;
import senla.mapper.ClientMapper;
import senla.entities.Client;
import senla.repository.api.ClientRepository;
import senla.servise.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Transactional
    @Override
    public List<ClientShortInfoDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientMapper::convertToShortDto)
                .toList();
    }
    @Transactional
    @Override
    public ClientShortInfoDTO findById(long uuid) {
        Client client = clientRepository.findById(uuid);
        return client != null ? ClientMapper.convertToShortDto(client) : null;
    }
    @Transactional
    @Override
    public boolean save(ClientDTOToEntity object) {
        Client client = ClientMapper.createClientDto(object);
        clientRepository.save(client);
        return client.getId() != null;
    }
    @Transactional
    @Override
    public void deleteById(long uuid){
        clientRepository.deleteById(uuid);
    }
}

