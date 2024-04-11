package senla.mapper;

import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserFullInfoDTO;
import senla.dto.user.UserShortInfoDTO;
import senla.entities.User;

public class UserMapper {
    public static UserFullInfoDTO convertToFullDto(User source){
        return source == null ? null : UserFullInfoDTO.builder()
                .email(source.getEmail())
                .secondName(source.getSecondname())
                .user_password(source.getUser_password())
                .firstName(source.getFirstname())
                .role(RoleMapper.convertToDto( source.getRole()))
                .build();

    }
    public static UserShortInfoDTO convertToShortDto(User source){
        return source == null ? null : UserShortInfoDTO.builder()
                .email(source.getEmail())
                .secondName(source.getSecondname())
                .firstName(source.getFirstname())
                .role(RoleMapper.convertToDto( source.getRole()))
                .build();

    }
    public static User createUserDto(UserDTOToEntity source){
        return source == null ? null : User.builder()
                .email(source.getEmail())
                .role(RoleMapper.createRoleDto(source.getRole()))
                .secondname(source.getSecondName())
                .firstname(source.getFirstName())
                .build();

    }
}
