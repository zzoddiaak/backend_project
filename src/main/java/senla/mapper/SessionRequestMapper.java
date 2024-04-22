package senla.mapper;

import senla.dto.sessionrequest.SessionRequestDTO;
import senla.dto.sessionrequest.SessionRequestDTOToEntity;
import senla.dto.sessionrequest.SessionRequestFullDTO;
import senla.entities.SessionRequest;

public class SessionRequestMapper {
    public static SessionRequestFullDTO convertToFullDto(SessionRequest source){
        return source == null ? null : SessionRequestFullDTO.builder()
                .psychologist(PsychologistMapper.convertToFullDto( source.getPsychologist()))
                .client(ClientMapper.convertToFullDto( source.getClient()))
                .problem(source.getProblem())
                .requestDate(source.getRequestDate())
                .build();

    }
    public static SessionRequestDTO convertToShortDto(SessionRequest source){
        return source == null ? null : SessionRequestDTO.builder()
                .psychologist(PsychologistMapper.convertToShortDto( source.getPsychologist()))
                .requestDate(source.getRequestDate())
                .client(ClientMapper.convertToShortDto( source.getClient()))
                .problem(source.getProblem())
                .build();

    }
    public static SessionRequest createSessionRequestDto(SessionRequestDTOToEntity source){
        return source == null ? null : SessionRequest.builder()
                .psychologist(PsychologistMapper.createPsychologistDto( source.getPsychologist()))
                .requestDate(source.getRequestDate())
                .client(ClientMapper.createClientDto( source.getClient()))
                .problem(source.getProblem())
                .build();
    }
}
