package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.mapper.ClientCardMapper;
import senla.entities.ClientCard;
import senla.repository.api.ClientCardRepository;
import senla.service.ClientCardService;

import java.util.List;
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ClientCardServiceImpl implements ClientCardService {

    private final ClientCardRepository clientCardRepository;

    @Override
    public List<ClientCardDTO> findAll() {
        return clientCardRepository.findAll().stream()
                .map(ClientCardMapper::convertToDto)
                .toList();
    }

    @Override
    public ClientCardDTO findById(long id) {
        ClientCard clientCard = clientCardRepository.findById(id);
        return clientCard != null ? ClientCardMapper.convertToDto(clientCard) : null;
    }

    @Override
    public boolean save(ClientCardDTOToEntity object) {
        ClientCard clientCard = ClientCardMapper.createClientCardDto(object);
        clientCardRepository.save(clientCard);
        return clientCard.getId() != null;
    }

    @Override
    public boolean update(long uuid, ClientCardDTOToEntity updateDTO) {
        ClientCard clientCard = clientCardRepository.findById(uuid);
        if (clientCard != null) {
            if (updateDTO.getDiagnosis() != null && !updateDTO.getDiagnosis().isEmpty())
                clientCard.setDiagnos(updateDTO.getDiagnosis());
            if (updateDTO.getRecommendations() != null && !updateDTO.getRecommendations().isEmpty())
                clientCard.setRecommendations(updateDTO.getRecommendations());
            clientCardRepository.save(clientCard);
            return true;
        }
        return false;
    }


    @Override
    public void deleteById(long uuid){
        clientCardRepository.deleteById(uuid);
    }
}


