package senla.mapper;

import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;
import senla.entities.ClientCard;

public class ClientCardMapper {
    public static ClientCardDTO convertToDto(ClientCard source){
        return source == null ? null : ClientCardDTO.builder()
                .psychologist(PsychologistMapper.convertToShortDto(source.getPsychologist()))
                .diagnosis(source.getDiagnos())
                .recommendations(source.getRecommendations())
                .build();

    }
    public static ClientCard createClientCardDto(ClientCardDTOToEntity source){
        return source == null ? null : ClientCard.builder()
                .psychologist(PsychologistMapper.createPsychologistDto(source.getPsychologist()))
                .diagnos(source.getDiagnosis())
                .recommendations(source.getRecommendations())
                .build();
    }
}
