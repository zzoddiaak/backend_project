package senla.mapper;

import senla.dto.psychologist.PsychologistDTOToEntity;
import senla.dto.psychologist.PsychologistFullDTO;
import senla.dto.psychologist.PsychologistShortDTO;
import senla.entities.Psychologist;

public class PsychologistMapper
{ public static PsychologistFullDTO convertToFullDto(Psychologist source){
    return source == null ? null : PsychologistFullDTO.builder()
            .user(UserMapper.convertToFullDto( source.getUser()))
            .hourlyRate(source.getHourlyRate())
            .ratingValue(source.getRatingValue())
            .experience(source.getExperience())
            .build();
}
    public static PsychologistShortDTO convertToShortDto(Psychologist source){
        return source == null ? null : PsychologistShortDTO.builder()
                .user(UserMapper.convertToShortDto( source.getUser()))
                .hourlyRate(source.getHourlyRate())
                .ratingValue(source.getRatingValue())
                .experience(source.getExperience())
                .build();
    }
    public static Psychologist createPsychologistDto(PsychologistDTOToEntity source) {
        if (source == null) {
            return null;
        }

        Integer ratingValue = source.getRatingValue();
        int rating = ratingValue != null ? ratingValue.intValue() : 0;

        return Psychologist.builder()
                .user(UserMapper.createUserDto(source.getUser()))
                .hourlyRate(source.getHourlyRate())
                .ratingValue(rating)
                .experience(source.getExperience())
                .build();
    }

}
