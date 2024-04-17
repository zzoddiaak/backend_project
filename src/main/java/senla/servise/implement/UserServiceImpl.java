package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.mapper.UserMapper;
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
import senla.entities.User;
import senla.repository.iface.UserRepository;
import senla.servise.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserShortInfoDTO> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::convertToShortDto)
                .toList();
    }

    @Override
    public UserShortInfoDTO findById(long uuid) {
        User user = userRepository.findById(uuid);
        return user != null ? UserMapper.convertToShortDto(user) : null;
    }

    @Override
    public boolean save(UserDTOToEntity object) {
        userRepository.save(UserMapper.createUserDto(object));
        return true;
    }

    @Override
    public boolean update(long uuid, UserDTOToEntity userUpdateDTO) {
        User user = userRepository.findById(uuid);
        if (user != null) {
            if (!userUpdateDTO.getFirstName().isEmpty()) {
                user.setFirstname(userUpdateDTO.getFirstName());
            }
            if (!userUpdateDTO.getSecondName().isEmpty()) {
                user.setSecondname(userUpdateDTO.getSecondName());
            }
            if (!userUpdateDTO.getEmail().isEmpty()) {
                user.setEmail(userUpdateDTO.getEmail());
            }
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(long uuid) {
         userRepository.deleteById(uuid);
    }
}
