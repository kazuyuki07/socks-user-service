package su.yuk1chan.userservice.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import su.yuk1chan.userservice.dto.UserDTO;
import su.yuk1chan.userservice.dto.UserPatchDTO;
import su.yuk1chan.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
    void fullUpdateProducer(UserDTO producerDTO, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchProducerUpdate(UserPatchDTO producerPatchDTO, @MappingTarget User user);
}
