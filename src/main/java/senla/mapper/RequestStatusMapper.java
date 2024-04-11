package senla.mapper;

import senla.dto.requeststatus.RequestStatusDTO;
import senla.dto.requeststatus.RequestStatusDTOToEntity;
import senla.entities.RequestStatus;

public class RequestStatusMapper {
    public static RequestStatusDTO convertToDto(RequestStatus source) {
        return source == null ? null : RequestStatusDTO.builder()
                .requestStatus(source.getRequestStatus())
                .build();
    }

    public static RequestStatus createRequestStatusDto(RequestStatusDTOToEntity source) {
        return source == null ? null : RequestStatus.builder()
                .requestStatus(source.getRequestStatus())
                .build();
    }
}
