package senla.dto.mapper;

import senla.dto.session.SessionDTOToEntity;
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestFullDTO;
import senla.entities.Session;
import senla.entities.SessionRequest;

import java.time.LocalDate;

public class SessionRequestMapper {
    public static SessionRequestFullDTO convertToFullDTO(Session source){
        return source == null ? null : SessionRequestFullDTO.builder()
                .psychologist(PsychologistMapper.convertToFullDTO( source.getPsychologist()))
                .client(ClientMapper.convertEntityToFullDTO( source.getClient()))


                .build();

    }
    public static SessionRequestDTO convertToShortDTO(Session source){
        return source == null ? null : SessionRequestDTO.builder()
                .psychologist(PsychologistMapper.convertToShortDTO( source.getPsychologist()))

                .client(ClientMapper.convertEntityToShortDTO( source.getClient()))

                .build();

    }
    public static SessionRequest convertDTOToEntity(SessionDTOToEntity source){
        return source == null ? null : SessionRequest.builder()
                .psychologist(PsychologistMapper.convertDTOToEntity( source.getPsychologist()))

                .client(ClientMapper.convertDTOToEntity( source.getClient()))

                .build();
    }
}
