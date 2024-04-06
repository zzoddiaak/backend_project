package senla.dto.mapper;

import lombok.experimental.UtilityClass;
import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientFullInfoDTO;
import senla.dto.client.ClientShortInfoDTO;
import senla.entities.Client;

@UtilityClass

public class ClientMapper {
    public static ClientFullInfoDTO convertEntityToFullDTO(Client source){
        return source == null ? null : ClientFullInfoDTO.builder()
                .uuid(source.getUuid())
                .clientCard(ClientCardMapper.convertEntityToDTO(source.getClientCard()))
                .user(UserMapper.convertToFullDTO(source.getUser()))
                .build();
    }
    public static ClientShortInfoDTO convertEntityToShortDTO(Client source){
        return source == null ? null : ClientShortInfoDTO.builder()
                .user(UserMapper.convertToShortDTO(source.getUser()))
                .clientCard(ClientCardMapper.convertEntityToDTO(source.getClientCard()))
                .build();

    }
    public static Client convertDTOToEntity(ClientDTOToEntity source){
        return source == null ? null : Client.builder()
                .user(UserMapper.convertDTOToEntity(source.getUser()))
                .clientCard(ClientCardMapper.convertDTOToEntity(source.getClientCard()))
                .build();
    }
}
