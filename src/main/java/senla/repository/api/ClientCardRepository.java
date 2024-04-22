package senla.repository.api;


import senla.entities.ClientCard;
import senla.entities.PaymentStatus;

import java.util.List;

public interface ClientCardRepository {
    ClientCard findById(Long uuid);
    List<ClientCard> findAll();
    void save(ClientCard clientCard);
    void deleteById(Long uuid);
    public List<ClientCard> findAllWithFetch(String recommendations);
    public List<ClientCard> findAllWithJoinFetch(String diagnos);
    public List<ClientCard> findAllWithDetails();

}
