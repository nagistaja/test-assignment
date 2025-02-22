package org.helmes.task.integration.exception;

import org.helmes.task.dto.ErrorResponse;
import org.helmes.task.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration")
public class GlobalExceptionHandlerIntegrationTest extends BaseIntegrationTest {

  @LocalServerPort
  private int port;

  private final TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  void whenInvalidToken_thenGlobalExceptionHandlerReturnsNotFound() {
    String url = "http://localhost:" + port + "/api/users/edit/invalid-token";
    ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(url, ErrorResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    ErrorResponse error = response.getBody();
    assertThat(error).isNotNull();
    assertThat(error.getStatus()).isEqualTo(404);
    assertThat(error.getError()).isEqualTo("Not Found");
  }
}
