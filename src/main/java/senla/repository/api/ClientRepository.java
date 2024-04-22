package senla.repository.api;

import senla.entities.*;

import java.util.List;

public interface ClientRepository {
    Client findById(Long uuid);
    List<Client> findAll();
    void save(Client client);
    void deleteById(Long uuid);
    public List<Client> findAllWithDetails();
}
