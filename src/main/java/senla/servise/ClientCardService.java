package senla.servise;

import senla.dto.clientcard.ClientCardDTO;
import senla.dto.clientcard.ClientCardDTOToEntity;

import java.util.List;

public interface ClientCardService {
    List<ClientCardDTO> findAll();
    ClientCardDTO findById(long uuid);
    boolean save(ClientCardDTOToEntity object);
    boolean update(long uuid, ClientCardDTOToEntity updateDTO);
    void deleteById(long uuid);
}


