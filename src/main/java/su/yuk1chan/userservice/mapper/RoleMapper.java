package su.yuk1chan.userservice.mapper;

import org.mapstruct.Mapper;
import su.yuk1chan.userservice.dto.RoleDTO;
import su.yuk1chan.userservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleDTOtoRole(RoleDTO roleDTO);
    RoleDTO roleToRoleDTO(Role role);
}
