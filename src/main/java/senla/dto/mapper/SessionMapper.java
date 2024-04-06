package senla.dto.mapper;

import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.session.SessionDTO;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.entities.Session;

public class SessionMapper {
    public static SessionDTO convertToFullDTO(Session source){
        return source == null ? null : SessionDTO.builder()
                .uuid(source.getUuid())
                .order(OrderMapper.convertToFullDTO( source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.convertToFullDTO( source.getPsychologist()))
                .course(CourseMapper.convertEntityToFullDTO( source.getCourse()))
                .client(ClientMapper.convertEntityToFullDTO( source.getClient()))
                .build();

    }
    public static SessionShortDTO convertToShortDTO(Session source){
        return source == null ? null : SessionShortDTO.builder()
                .order(OrderMapper.convertToShortDTO(source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.convertToShortDTO(source.getPsychologist()))
                .course(CourseMapper.convertEntityToShortDTO( source.getCourse()))
                .client(ClientMapper.convertEntityToShortDTO (source.getClient()))

                .build();

    }
    public static Session convertDTOToEntity(SessionDTOToEntity source){
        return source == null ? null : Session.builder()
                .order(OrderMapper.convertDTOToEntity(source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.convertDTOToEntity(source.getPsychologist()))
                .course(CourseMapper.convertDTOToEntity( source.getCourse()))
                .client(ClientMapper.convertDTOToEntity(source.getClient()))

                .build();
    }
}
