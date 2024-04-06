package senla.dto.mapper;

import senla.dto.psychologist.PsychologistShortDTO;
import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.entities.RequestStatus;

public class RequestStatusMapper {
    public static RequestStatusDTO convertToDTO(RequestStatus source){
        return source == null ? null : RequestStatusDTO.builder()
                .requestStatus(source.getRequestStatus())
                .build();

    }
    public static RequestStatus convertDTOToEntity(RequestStatusDTOToEntity source){
        return source == null ? null : RequestStatus.builder()
                .requestStatus(source.getRequestStatus())
                .build();

    }

}
