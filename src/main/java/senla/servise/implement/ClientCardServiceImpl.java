package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.mapper.ClientCardMapper;
import senla.entities.ClientCard;
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
                .map(ClientCardMapper::convertToDto)
                .toList();

    }
    @Override
    public ClientCardDTO findById(long uuid){
        return ClientCardMapper.convertToDto(clientCardRepository.findById(uuid));
    }
    @Override
    public void save(ClientCardDTOToEntity object){
        clientCardRepository.save(ClientCardMapper.createClientCardDto(object));
    }
    @Override
    public void update(long uuid, ClientCardDTOToEntity updateDTO){
        ClientCard clientCard = clientCardRepository.findById(uuid);
        if (!updateDTO.getDiagnosis().isEmpty()) clientCard.setDiagnos(updateDTO.getDiagnosis());
        if (!updateDTO.getRecommendations().isEmpty()) clientCard.setRecommendations(updateDTO.getRecommendations());
    }
    @Override
    public void deleteById(long uuid){
        clientCardRepository.deleteById(uuid);
    }

}
