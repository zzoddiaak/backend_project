package senla.mapper;

import lombok.experimental.UtilityClass;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.client.ClientShortInfoDTO;
import senla.entities.Client;

@UtilityClass

public class ClientMapper {
    public static ClientFullInfoDTO convertToFullDto(Client source){
        return source == null ? null : ClientFullInfoDTO.builder()
                .clientCard(ClientCardMapper.convertToDto(source.getClientCard()))
                .user(UserMapper.convertToFullDto(source.getUser()))
                .build();
    }
    public static ClientShortInfoDTO convertToShortDto(Client source){
        return source == null ? null : ClientShortInfoDTO.builder()
                .user(UserMapper.convertToShortDto(source.getUser()))
                .clientCard(ClientCardMapper.convertToDto(source.getClientCard()))
                .build();

    }
    public static Client createClientDto(ClientDTOToEntity source){
        return source == null ? null : Client.builder()
                .user(UserMapper.createUserDto(source.getUser()))
                .clientCard(ClientCardMapper.createClientCardDto(source.getClientCard()))
                .build();
    }
}
