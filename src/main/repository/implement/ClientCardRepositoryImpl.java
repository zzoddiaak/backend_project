package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Client;
import senla.entities.ClientCard;
import senla.entities.Psychologist;
import senla.repository.AbstractRepository;
import senla.repository.iface.ClientCardRepository;
import senla.repository.iface.ClientRepository;
@Repository
public class ClientCardRepositoryImpl extends AbstractRepository<ClientCard> implements ClientCardRepository {
    public ClientCardRepositoryImpl(){
        save(ClientCard.builder()
                .diagnos("Стресс")
                .recommendations("Лежать")
                .build());
        save(ClientCard.builder()
                .diagnos("Депрессия")
                .recommendations("Спать")
                .build());
    }



}
