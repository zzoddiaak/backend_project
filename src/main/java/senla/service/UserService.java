package senla.service;

import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;

import java.util.List;

public interface UserService {
    List<UserShortInfoDTO> findAll();
    UserShortInfoDTO findById(long uuid);
    boolean save(UserDTOToEntity object);
    boolean update(long uuid, UserDTOToEntity userUpdateDTO);
    void deleteById(long uuid);
}
