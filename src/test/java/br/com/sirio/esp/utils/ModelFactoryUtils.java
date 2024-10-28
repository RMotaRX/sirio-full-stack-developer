package br.com.sirio.esp.utils;

import br.com.sirio.esp.domain.dto.request.AddressRequest;
import br.com.sirio.esp.domain.dto.request.PersonaRequest;
import br.com.sirio.esp.domain.dto.request.UserRequest;
import br.com.sirio.esp.domain.models.Address;
import br.com.sirio.esp.domain.models.Persona;
import br.com.sirio.esp.domain.models.User;
import br.com.sirio.esp.domain.models.enums.StatusEnum;
import br.com.sirio.esp.domain.models.enums.UserRoleEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ModelFactoryUtils {

  public static List<User> buildUsers() {
    return buildUserRequest().stream()
        .map(request -> new User(
            request.getId(),
            request.getEmail(),
            request.getPassword(),
            request.getRole()
        ))
        .collect(Collectors.toList());
  }

  public static List<UserRequest> buildUserRequest() {
    return List.of(
        UserRequest.builder()
            .id(1L)
            .email("daniloaraujocosta@teleworm.us")
            .password("DaniloAraujoCosta@2024")
            .role(UserRoleEnum.ADMIN)
            .build(),
        UserRequest.builder()
            .id(2L)
            .email("IgorFerreiraPinto@dayrep.com")
            .password("IgorFP_!_")
            .role(UserRoleEnum.ADMIN)
            .build()
    );
  }

  public static List<Persona> buildPersonas(List<User> users) {
    User associatedUser = buildUsers().get(0);

    return Stream.of(buildPersonaRequest())
        .map(request -> new Persona(
            request.getId(),
            request.getName(),
            request.getCpf(),
            request.getBirthdate(),
            request.getCellphone(),
            request.getAddress() != null ? new Address(
                request.getAddress().getStreet(),
                request.getAddress().getNumber(),
                request.getAddress().getAdditionalInformation(),
                request.getAddress().getNeighborhood(),
                request.getAddress().getZipCode(),
                request.getAddress().getCity(),
                request.getAddress().getState()
            ) : null,
            request.getStatus(),
            request.getCreationAt(),
            request.getUpdatedAt(),
            request.getRemovalAt(),
            users.get(0)
        ))
        .collect(Collectors.toList());
  }

  public static PersonaRequest buildPersonaRequest() {
    return PersonaRequest.builder()
        .id(1L)
        .name("Nicolash Ribeiro Melo")
        .cpf("63862560422")
        .birthdate(LocalDate.of(1939, 1, 19))
        .cellphone("+55 51 952 519 967")
        .address(buildAddressRequest())
        .status(StatusEnum.ACTIVE)
        .creationAt(LocalDateTime.now())
        .updatedAt(null)
        .removalAt(null)
        .build();
  }

  public static AddressRequest buildAddressRequest() {
    return AddressRequest.builder()
        .street("Travessa Herbert")
        .number("1204")
        .additionalInformation("Apto. 10")
        .neighborhood("Herbert")
        .zipCode("94035-210")
        .city("Gravataí")
        .state("RS")
        .build();
  }
}
