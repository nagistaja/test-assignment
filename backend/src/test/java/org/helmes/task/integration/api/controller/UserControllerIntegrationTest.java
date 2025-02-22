package org.helmes.task.integration.api.controller;

import org.helmes.task.dto.UserDto;
import org.helmes.task.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerIntegrationTest extends BaseIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String getBaseUrl() {
    return "http://localhost:" + port + "/api/users";
  }

  @Test
  void submitValidUser_ReturnsEditToken() {
    UserDto validUser = UserDto.builder()
            .name("Test User")
            .sectorIds(java.util.List.of(1L)) // Ensure sector with ID 1 exists from your migrations
            .agreeToTerms(true)
            .build();

    ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/save",
            toRequest(validUser),
            Map.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    Map body = response.getBody();
    assertThat(body).isNotNull();
    assertThat(body).containsKey("editToken");
  }

  @Test
  void submitInvalidUser_ReturnsBadRequest() {
    UserDto invalidUser = UserDto.builder()
            .name("") // invalid
            .sectorIds(Collections.emptyList()) // invalid
            .agreeToTerms(false) // invalid
            .build();

    ResponseEntity<Map> response = restTemplate.postForEntity(
            getBaseUrl() + "/save",
            toRequest(invalidUser),
            Map.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  @Test
  void getUserByValidToken_ReturnsUserDetails() {
    // 1) Save user
    UserDto user = UserDto.builder()
            .name("Integration Test User")
            .sectorIds(java.util.List.of(1L))
            .agreeToTerms(true)
            .build();

    ResponseEntity<Map> saveResponse = restTemplate.postForEntity(
            getBaseUrl() + "/save",
            toRequest(user),
            Map.class
    );
    assertThat(saveResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    String editToken = (String) Objects.requireNonNull(saveResponse.getBody()).get("editToken");

    // 2) Retrieve user
    ResponseEntity<UserDto> getResponse =
            restTemplate.getForEntity(getBaseUrl() + "/edit/" + editToken, UserDto.class);

    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    UserDto retrieved = getResponse.getBody();
    assertThat(retrieved).isNotNull();
    assertThat(retrieved.getName()).isEqualTo("Integration Test User");
  }

  @Test
  void getUserByInvalidToken_ReturnsNotFound() {
    String invalidToken = "bad-token"; // Not a valid UUID
    ResponseEntity<Map> response =
            restTemplate.getForEntity(getBaseUrl() + "/edit/" + invalidToken, Map.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void updateUserWithValidData_ReturnsUpdateSuccess() {
    // 1) Create user
    UserDto user = UserDto.builder()
            .name("User To Update")
            .sectorIds(java.util.List.of(1L))
            .agreeToTerms(true)
            .build();

    ResponseEntity<Map> saveResponse = restTemplate.postForEntity(
            getBaseUrl() + "/save",
            toRequest(user),
            Map.class
    );
    assertThat(saveResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    String editToken = (String) saveResponse.getBody().get("editToken");



    // 2) Update user
    UserDto updatedUser = UserDto.builder()
            .name("Updated Name")
            .sectorIds(java.util.List.of(1L, 2L))
            .agreeToTerms(true)
            .build();

    ResponseEntity<String> updateResponse = restTemplate.exchange(
            getBaseUrl() + "/update/" + editToken,
            HttpMethod.PUT,
            toRequest(updatedUser),
            String.class
    );

    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(updateResponse.getBody()).contains("Update successful");

    // 3) Retrieve to confirm update
    ResponseEntity<UserDto> getResponse =
            restTemplate.getForEntity(getBaseUrl() + "/edit/" + editToken, UserDto.class);

    assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    UserDto retrieved = getResponse.getBody();
    assertThat(retrieved).isNotNull();
    assertThat(retrieved.getName()).isEqualTo("Updated Name");
  }

  @Test
  void updateUserWithInvalidData_ReturnsBadRequest() {
    // 1) Create user
    UserDto user = UserDto.builder()
            .name("User For Invalid Update")
            .sectorIds(java.util.List.of(1L))
            .agreeToTerms(true)
            .build();
    ResponseEntity<Map> saveResponse = restTemplate.postForEntity(
            getBaseUrl() + "/save",
            toRequest(user),
            Map.class
    );
    assertThat(saveResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    String editToken = (String) saveResponse.getBody().get("editToken");

    // 2) Try an invalid update
    UserDto invalidUpdate = UserDto.builder()
            .name("") // invalid
            .sectorIds(Collections.emptyList()) // invalid
            .agreeToTerms(false) // invalid
            .build();

    ResponseEntity<String> updateResponse = restTemplate.exchange(
            getBaseUrl() + "/update/" + editToken,
            HttpMethod.PUT,
            toRequest(invalidUpdate),
            String.class
    );
    assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

  /**
   * Helper method to create a JSON request entity from a DTO.
   */
  private HttpEntity<UserDto> toRequest(UserDto dto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(dto, headers);
  }
}
