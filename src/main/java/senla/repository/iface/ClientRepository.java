package senla.repository.iface;

import senla.entities.Client;

import java.util.List;

public interface ClientRepository {
    Client findById(Long uuid);
    List<Client> findAll();
    void save(Client client);
    void deleteById(Long uuid);
}
