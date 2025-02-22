package org.helmes.task.integration.security;

import org.helmes.task.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("integration")
public class SecurityConfigIntegrationTest extends BaseIntegrationTest {

  @LocalServerPort
  private int port;

  private final TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  void unauthenticatedAccessProtectedEndpointReturnsForbidden() {
    String url = "http://localhost:" + port + "/api/admin";
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }
}
