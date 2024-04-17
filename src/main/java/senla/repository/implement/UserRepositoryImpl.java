package senla.repository.implement;

import org.springframework.stereotype.Repository;
import senla.entities.User;
import senla.repository.AbstractRepository;
import senla.repository.iface.UserRepository;
@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
    public UserRepositoryImpl(){

        save(User.builder()
                .email("ivanov@mail.ru")
                .userPassword("1341341")
                .firstname("Ivanov")
                .secondname("Ivan")
                .build());
            save(User.builder()
                    .email("Yarik@mai.ru")
                    .userPassword("1341341")
                    .firstname("Yarik")
                    .secondname("Novik")
                    .build());
    }
}
