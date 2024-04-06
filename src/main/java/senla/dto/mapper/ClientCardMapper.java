package senla.dto.mapper;

import senla.dto.client.ClientFullInfoDTO;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.entities.ClientCard;

public class ClientCardMapper {
    public static ClientCardDTO convertEntityToDTO(ClientCard source){
        return source == null ? null : ClientCardDTO.builder()
                .psychologist(PsychologistMapper.convertToShortDTO(source.getPsychologist()))
                .diagnosis(source.getDiagnos())
                .recommendations(source.getRecommendations())
                .build();

    }
    public static ClientCard convertDTOToEntity(ClientCardDTOToEntity source){
        return source == null ? null : ClientCard.builder()
                .psychologist(PsychologistMapper.convertDTOToEntity(source.getPsychologist()))
                .diagnos(source.getDiagnosis())
                .recommendations(source.getRecommendations())
                .build();
    }
}
