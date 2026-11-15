package su.yuk1chan.userservice.repository.specification;


import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import su.yuk1chan.userservice.enums.Status;
import su.yuk1chan.userservice.entity.User;

import java.util.List;

public class UserSpecification {
    public static Specification<User> firstNameFilter(String firstName) {
        return (root, _, builder) ->
            (firstName == null || firstName.isEmpty()) ? null : builder.like(
                    builder.lower(root.get("firstName")),
                    "%" + firstName.trim().toLowerCase() + "%"
            );
    }

    public static Specification<User> firstNamesFilter(List<String> firstNames) {
        return (root, _, builder) -> {
            if (firstNames == null || firstNames.isEmpty()) {
                    return null;
            }

            Predicate[] predicates = firstNames.stream()
                      .map(String::trim)
                      .filter(firstName -> !firstName.isEmpty())
                      .map(firstName -> builder.like(
                              builder.lower(root.get("firstName")),
                                      "%" + firstName.toLowerCase() + "%"
                              )
                      )
                      .toArray(Predicate[]::new);

            if (predicates.length == 0) {
                  return null;
            }

            return builder.or(predicates);
        };
    }

    public static Specification<User> lastNamesFilter(List<String> lastNames) {
        return (root, _, builder) -> {
            if (lastNames == null || lastNames.isEmpty()) {
                return null;
            }

            Predicate[] predicates = lastNames.stream()
                    .map(String::trim)
                    .filter(lastName -> !lastName.isEmpty())
                    .map(lastName -> builder.like(
                            builder.lower(root.get("lastName")),
                                    "%" + lastName.toLowerCase() + "%"
                            )
                    )
                    .toArray(Predicate[]::new);

            if (predicates.length == 0) {
                return null;
            }

            return builder.or(predicates);
        };
    }

    public static Specification<User> lastNameFilter(String lastName) {
        return (root, _, builder) ->
                (lastName == null || lastName.isEmpty()) ? null : builder.like(
                        builder.lower(root.get("lastName")),
                        "%" + lastName.toLowerCase() + "%"
                );
    }

    public static Specification<User> companyFilter(String company) {
        return (root, _, builder) ->
                (company == null || company.isEmpty()) ? null : builder.like(
                        builder.lower(root.get("company")),
                        "%" + company.trim().toLowerCase() + "%"
                );
    }

    public static Specification<User> companiesFilter(List<String> companies) {
        return (root, _, builder) -> {
            if (companies == null || companies.isEmpty()) {
                return null;
            }

            Predicate[] predicates = companies.stream()
                .map(String::trim)
                .filter(company -> !company.isEmpty())
                .map(company -> builder.like(
                                builder.lower(root.get("company")),
                                "%" + company.toLowerCase() + "%")
                )
                .toArray(Predicate[]::new);

            if (predicates.length == 0) {
                return null;
            }

            return builder.or(predicates);
        };
    }

    public static Specification<User> phoneNumberFilter(String phoneNumber) {
        return (root, _, builder) ->
                (phoneNumber == null || phoneNumber.isEmpty()) ? null : builder.like(
                        builder.lower(root.get("phoneNumber")),
                        "%" + phoneNumber.trim().toLowerCase() + "%"
                );
    }

    public static Specification<User> phoneNumbersFilter(List<String> phoneNumbers) {
        return (root, _, builder) -> {
            if (phoneNumbers == null || phoneNumbers.isEmpty()) {
                return null;
            }

            Predicate[] predicates = phoneNumbers.stream()
                    .map(String::trim)
                    .filter(phoneNumber -> !phoneNumber.isEmpty())
                    .map(phoneNumber -> builder.like(
                                    builder.lower(root.get("phoneNumber")),
                                    "%" + phoneNumber.toLowerCase() + "%"
                            )
                    )
                    .toArray(Predicate[]::new);

            if (predicates.length == 0) {
                return null;
            }

            return builder.or(predicates);
        };
    }

    public static Specification<User> emailFilter(String email) {
        return (root, _, builder) ->
                (email == null || email.isEmpty()) ? null : builder.like(builder.lower(
                        root.get("email")),
                        "%" + email.trim().toLowerCase() + "%");
    }

    public static Specification<User> emailsFilter(List<String> emails) {
        return (root, _, builder) -> {
            if (emails == null || emails.isEmpty()) {
                return null;
            }

            Predicate[] predicates = emails.stream()
                    .map(String::trim)
                    .filter(email -> !email.isEmpty())
                    .map(email -> builder.like(
                                    builder.lower(root.get("email")),
                                    "%" + email.toLowerCase() + "%"
                            )
                    )
                    .toArray(Predicate[]::new);

            if (predicates.length == 0) {
                return null;
            }

            return builder.or(predicates);
        };
    }

    public static Specification<User> statusFilter(Status status) {
        return (root, _, builder) ->
                status == null ? null : builder.equal(root.get("status"), status);
    }

    public static Specification<User> roleIdFilter(Long roleId) {
        return (root, _, builder) ->
            roleId == null ? null : builder.equal(root.get("role").get("id"), roleId);
    }
}
