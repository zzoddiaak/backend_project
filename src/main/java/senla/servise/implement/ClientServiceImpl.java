package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;
import senla.mapper.ClientMapper;
import senla.repository.iface.ClientRepository;
import senla.servise.ClientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public List<ClientShortInfoDTO> findAll(){
        return clientRepository.findAll().stream()
                .map(ClientMapper::convertToShortDto)
                .toList();

    }
    @Override
    public ClientShortInfoDTO findById(long uuid){
        return ClientMapper.convertToShortDto(clientRepository.findById(uuid));
    }
    @Override
    public void save(ClientDTOToEntity object){
        clientRepository.save(ClientMapper.createClientDto(object));
    }
    @Override
    public void deleteById(long uuid){
        clientRepository.deleteById(uuid);
    }


}
