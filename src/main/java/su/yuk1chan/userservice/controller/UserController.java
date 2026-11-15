package su.yuk1chan.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import su.yuk1chan.userservice.dto.PagedResponse;
import su.yuk1chan.userservice.dto.UserDTO;
import su.yuk1chan.userservice.dto.UserPatchDTO;
import su.yuk1chan.userservice.entity.User;
import su.yuk1chan.userservice.enums.Status;
import su.yuk1chan.userservice.mapper.UserMapper;
import su.yuk1chan.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/producer_service")
@RequiredArgsConstructor
public class UserController {
    private final UserService UserService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addProducer(@RequestBody UserDTO userDTO) {
        User user = UserService.addUser(userDTO);
        return userMapper.userToUserDTO(user);
    }

    @DeleteMapping("/{producerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducer(@PathVariable Long producerId) {
        UserService.deleteUser(producerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedResponse<UserDTO> getProducers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "company") String sort,
            @RequestParam(required = false, name = "first_name") String firstName,
            @RequestParam(required = false, name = "first_names") List<String> firstNames,
            @RequestParam(required = false, name = "last_name") String lastName,
            @RequestParam(required = false, name = "last_names") List<String> lastNames,
            @RequestParam(required = false) String company,
            @RequestParam(required = false, name = "companies") List<String> companies,
            @RequestParam(required = false, name = "phone_number") String phoneNumber,
            @RequestParam(required = false, name = "phone_numbers") List<String> phoneNumbers,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) List<String> emails,
            @RequestParam(required = false) Long roleId,
            @RequestParam(required = false) Status status
    ) {
        return UserService.getUsers(
                page,
                size,
                firstName,
                firstNames,
                lastName,
                lastNames,
                company,
                companies,
                phoneNumber,
                phoneNumbers,
                email,
                emails,
                status,
                roleId,
                sort
        );
    }

    @PutMapping("/{producerId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO fullUpdateProducer(
            @PathVariable Long producerId,
            @RequestBody UserDTO updatedUser) {
        User updateUserResult = UserService.fullUpdateProducer(producerId, updatedUser);

        return userMapper.userToUserDTO(updateUserResult);
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO partUpdateProducer(
            @PathVariable Long userId,
            @RequestBody UserPatchDTO patchedUser) {
        User updateUserResult = UserService.partUpdateProducer(userId, patchedUser);

        return userMapper.userToUserDTO(updateUserResult);
    }
}
