package senla.dto.mapper;

import senla.dto.session.SessionDTOToEntity;
import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.dto.sessionrequest.SessionRequestFullDTO;
import senla.entities.Session;
import senla.entities.SessionRequest;

import java.time.LocalDate;

public class SessionRequestMapper {
    public static SessionRequestFullDTO convertToFullDTO(SessionRequest source){
        return source == null ? null : SessionRequestFullDTO.builder()
                .psychologist(PsychologistMapper.convertToFullDTO( source.getPsychologist()))
                .client(ClientMapper.convertEntityToFullDTO( source.getClient()))


                .build();

    }
    public static SessionRequestDTO convertToShortDTO(SessionRequest source){
        return source == null ? null : SessionRequestDTO.builder()
                .psychologist(PsychologistMapper.convertToShortDTO( source.getPsychologist()))
                .requestDate(source.getRequestDate())
                .client(ClientMapper.convertEntityToShortDTO( source.getClient()))
                .problem(source.getProblem())
                .build();

    }
    public static SessionRequest convertDTOToEntity(SessionRequestDTOToEntity source){
        return source == null ? null : SessionRequest.builder()
                .psychologist(PsychologistMapper.convertDTOToEntity( source.getPsychologist()))
                .requestDate(source.getRequestDate())
                .client(ClientMapper.convertDTOToEntity( source.getClient()))
                .problem(source.getProblem())
                .build();
    }
}
