package senla.dto.mapper;

import senla.dto.user.UserDTOToEntity;
import senla.dto.user.UserFullInfoDTO;
import senla.dto.user.UserShortInfoDTO;
import senla.entities.User;

public class UserMapper {
    public static UserFullInfoDTO convertToFullDTO(User source){
        return source == null ? null : UserFullInfoDTO.builder()
                .uuid(source.getUuid())
                .email(source.getEmail())
                .secondName(source.getSecondname())
                .user_password(source.getUser_password())
                .firstName(source.getFirstname())
                .role(RoleMapper.convertToDTO( source.getRole()))
                .build();

    }
    public static UserShortInfoDTO convertToShortDTO(User source){
        return source == null ? null : UserShortInfoDTO.builder()
                .email(source.getEmail())
                .secondName(source.getSecondname())
                .firstName(source.getFirstname())
                .role(RoleMapper.convertToDTO( source.getRole()))
                .build();

    }
    public static User convertDTOToEntity(UserDTOToEntity source){
        return source == null ? null : User.builder()
                .email(source.getEmail())
                .role(RoleMapper.convertDTOToEntity(source.getRole()))
                .secondname(source.getSecondName())
                .firstname(source.getFirstName())
                .build();

    }
}
