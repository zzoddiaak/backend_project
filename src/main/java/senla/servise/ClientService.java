package senla.servise;

import senla.dto.client.ClientDTOToEntity;
import senla.dto.client.ClientShortInfoDTO;

import java.util.List;

public interface ClientService {
    List<ClientShortInfoDTO> findAll();
    ClientShortInfoDTO findById(long uuid);
    boolean save(ClientDTOToEntity object);
    void deleteById(long uuid);

}

