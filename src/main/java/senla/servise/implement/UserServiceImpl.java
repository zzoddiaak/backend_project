package senla.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import senla.dto.mapper.SessionMapper;
import senla.dto.mapper.UserMapper;
import senla.dto.session.SessionDTOToEntity;
import senla.dto.session.SessionShortDTO;
import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserShortInfoDTO;
import senla.repository.iface.SessionRepository;
import senla.repository.iface.UserRepository;
import senla.servise.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<UserShortInfoDTO> findAll(){
        return userRepository.findAll().stream()
                .map(UserMapper::convertToShortDTO)
                .toList();

    }
    @Override
    public UserShortInfoDTO findById(long uuid){
        return UserMapper.convertToShortDTO(userRepository.findById(uuid));
    }
    @Override
    public void save(UserDTOToEntity object){
        userRepository.save(UserMapper.convertDTOToEntity(object));
    }
    @Override
    public void deleteById(long uuid){
        userRepository.deleteById(uuid);
    }
}
