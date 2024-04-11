package senla.servise;

import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
import java.util.List;

public interface UserService {
    List<UserShortInfoDTO> findAll();
    UserShortInfoDTO findById(long uuid);
    void save(UserDTOToEntity object);
    void update(long uuid, UserDTOToEntity userUpdateDTO);
    void deleteById(long uuid);
}
