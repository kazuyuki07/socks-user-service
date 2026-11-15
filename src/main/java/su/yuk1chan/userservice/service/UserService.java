package su.yuk1chan.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import su.yuk1chan.userservice.dto.PagedResponse;
import su.yuk1chan.userservice.dto.UserPatchDTO;
import su.yuk1chan.userservice.enums.ProducerSort;
import su.yuk1chan.userservice.enums.Status;
import su.yuk1chan.userservice.exceptions.NotFoundException;
import su.yuk1chan.userservice.mapper.UserMapper;
import su.yuk1chan.userservice.dto.UserDTO;
import su.yuk1chan.userservice.entity.User;
import su.yuk1chan.userservice.repository.UserRepository;
import su.yuk1chan.userservice.repository.specification.UserSpecification;

import java.util.List;

import static su.yuk1chan.userservice.repository.specification.UserSpecification.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User addUser(UserDTO userDTO) {
        User user = userMapper.userDTOtoUser(userDTO);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public PagedResponse<UserDTO> getUsers(
            Integer page,
            Integer size,
            String firstName,
            List<String> firstNames,
            String lastName,
            List<String> lastNames,
            String company,
            List<String> companies,
            String phoneNumber,
            List<String> phoneNumbers,
            String email,
            List<String> emails,
            Status status,
            Long roleId,
            String sort
    ) {
        Specification<User> producersSpecification = Specification.where(
                UserSpecification.firstNameFilter(firstName)
                        .and(firstNamesFilter(firstNames))
                        .and(lastNameFilter(lastName))
                        .and(lastNamesFilter(lastNames))
                        .and(companyFilter(company))
                        .and(companiesFilter(companies))
                        .and(phoneNumberFilter(phoneNumber))
                        .and(phoneNumbersFilter(phoneNumbers))
                        .and(emailFilter(email))
                        .and(emailsFilter(emails))
                        .and(statusFilter(status))
                        .and(roleIdFilter(roleId))
        );

        ProducerSort producerSort = ProducerSort.of(sort);
        Sort sorted = Sort.by(
                producerSort.getDirection(),
                producerSort.getEntityValue()
        );

        Page<User> producers = userRepository.findAll(producersSpecification,
                PageRequest.of(page, size, sorted)
        );

        return PagedResponse.from(producers
                .map(userMapper::userToUserDTO)
        );
    }

    public User fullUpdateProducer(Long id, UserDTO updatedUser) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь " + id));

        userMapper.fullUpdateProducer(updatedUser, foundUser);

        return userRepository.save(foundUser);
    }

    public User partUpdateProducer(Long id, UserPatchDTO patchedUser) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не найден пользователь " + id));

        userMapper.patchProducerUpdate(patchedUser, foundUser);

        return userRepository.save(foundUser);
    }
}
