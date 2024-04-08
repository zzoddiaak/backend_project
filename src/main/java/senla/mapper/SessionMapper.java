package senla.mapper;

import senla.dto.session.SessionDTO;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.entities.Session;

public class SessionMapper {
    public static SessionDTO convertToFullDto(Session source){
        return source == null ? null : SessionDTO.builder()
                .order(OrderMapper.convertToFullDto( source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.convertToFullDto( source.getPsychologist()))
                .course(CourseMapper.convertToFullDto( source.getCourse()))
                .client(ClientMapper.convertToFullDto( source.getClient()))
                .build();

    }
    public static SessionShortDTO convertToShortDto(Session source){
        return source == null ? null : SessionShortDTO.builder()
                .order(OrderMapper.convertToShortDto(source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.convertToShortDto(source.getPsychologist()))
                .course(CourseMapper.convertToShortDto( source.getCourse()))
                .client(ClientMapper.convertToShortDto(source.getClient()))

                .build();

    }
    public static Session createSessionDto(SessionDTOToEntity source){
        return source == null ? null : Session.builder()
                .order(OrderMapper.createOrderDto(source.getOrder()))
                .startSession(source.getStartSession())
                .finalSession(source.getFinalSession())
                .psychologist(PsychologistMapper.createPsychologistDto(source.getPsychologist()))
                .course(CourseMapper.createCourseDto( source.getCourse()))
                .client(ClientMapper.createClientDto(source.getClient()))

                .build();
    }
}
