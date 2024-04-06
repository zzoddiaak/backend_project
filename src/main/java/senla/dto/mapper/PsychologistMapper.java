package senla.dto.mapper;

import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistFullDTO;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.entities.Psychologist;

public class PsychologistMapper
{ public static PsychologistFullDTO convertToFullDTO(Psychologist source){
    return source == null ? null : PsychologistFullDTO.builder()
            .uuid(source.getUuid())
            .user(UserMapper.convertToFullDTO( source.getUser()))
            .hourlyRate(source.getHourlyRate())
            .ratingValue(source.getRatingValue())
            .experience(source.getExperience())
            .build();
}
    public static PsychologistShortDTO convertToShortDTO(Psychologist source){
        return source == null ? null : PsychologistShortDTO.builder()
                .user(UserMapper.convertToShortDTO( source.getUser()))
                .hourlyRate(source.getHourlyRate())
                .ratingValue(source.getRatingValue())
                .experience(source.getExperience())
                .build();
    }
    public static Psychologist convertDTOToEntity(PsychologistDTOToEntity source){
        return source == null ? null : Psychologist.builder()
                .user(UserMapper.convertDTOToEntity(source.getUser()))
                .hourlyRate(source.getHourlyRate())
                .ratingValue(source.getRatingValue())
                .experience(source.getExperience())
                .build();

    }
}
