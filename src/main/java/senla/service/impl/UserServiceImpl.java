package senla.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import senla.mapper.UserMapper;
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
import senla.entities.User;
import senla.repository.api.UserRepository;
import senla.service.UserService;

import java.util.List;
@Transactional(readOnly = true)
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
            if (userUpdateDTO.getFirstName() != null && !userUpdateDTO.getFirstName().isEmpty()) {
                user.setFirstname(userUpdateDTO.getFirstName());
            }
            if (userUpdateDTO.getSecondName() != null &&!userUpdateDTO.getSecondName().isEmpty()) {
                user.setSecondname(userUpdateDTO.getSecondName());
            }
            if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().isEmpty()) {
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
    public void createNewUser(UserDTOToEntity userDTOToEntity) {
        User user = UserMapper.createUserDto(userDTOToEntity);
    }
}
