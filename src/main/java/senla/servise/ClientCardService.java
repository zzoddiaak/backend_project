package senla.servise;

import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;
import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;

import java.util.List;

public interface ClientCardService {
    List<ClientCardDTO> findAll();
    ClientCardDTO findById(long uuid);
    void save(ClientCardDTOToEntity object);
    void deleteById(long uuid);
}
