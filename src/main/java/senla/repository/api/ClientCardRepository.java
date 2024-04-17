package senla.repository.api;


import senla.entities.ClientCard;

import java.util.List;

public interface ClientCardRepository {
    ClientCard findById(Long uuid);
    List<ClientCard> findAll();
    void save(ClientCard clientCard);
    void deleteById(Long uuid);

}
