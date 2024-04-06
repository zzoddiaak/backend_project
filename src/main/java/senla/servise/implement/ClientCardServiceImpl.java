package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.dto.mapper.ClientCardMapper;
import senla.repository.iface.ClientCardRepository;
import senla.servise.ClientCardService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClientCardServiceImpl implements ClientCardService {
    private final ClientCardRepository clientCardRepository;

    @Override
    public List<ClientCardDTO> findAll(){
        return clientCardRepository.findAll().stream()
                .map(ClientCardMapper::convertEntityToDTO)
                .toList();

    }
    @Override
    public ClientCardDTO findById(long uuid){
        return ClientCardMapper.convertEntityToDTO(clientCardRepository.findById(uuid));
    }
    @Override
    public void save(ClientCardDTOToEntity object){
        clientCardRepository.save(ClientCardMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        clientCardRepository.deleteById(uuid);
    }

}
