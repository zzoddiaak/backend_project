package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.Client;
import senla.repository.AbstractRepository;
import senla.repository.iface.ClientRepository;
@Repository

public class ClientRepositoryImpl extends AbstractRepository<Client> implements ClientRepository {
    public ClientRepositoryImpl(){
        save(Client.builder()

                .build());
        save(Client.builder()

                .build());
    }
}
