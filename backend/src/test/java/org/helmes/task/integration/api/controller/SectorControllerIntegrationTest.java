package org.helmes.task.integration.api.controller;

import org.helmes.task.dto.SectorDto;
import org.helmes.task.integration.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class SectorControllerIntegrationTest extends BaseIntegrationTest {

  @LocalServerPort
  private int port;

  private final TestRestTemplate restTemplate = new TestRestTemplate();

  @Test
  void getHierarchicalSectors_ReturnsNonEmptyList() {
    String url = "http://localhost:" + port + "/api/sectors/hierarchical";
    ResponseEntity<SectorDto[]> response = restTemplate.getForEntity(url, SectorDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    SectorDto[] sectors = response.getBody();
    assertThat(sectors).isNotNull();
    assertThat(sectors.length).isGreaterThan(0);
  }

  @Test
  void getFlatSectors_ReturnsNonEmptyList() {
    String url = "http://localhost:" + port + "/api/sectors/flat";
    ResponseEntity<SectorDto[]> response = restTemplate.getForEntity(url, SectorDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    SectorDto[] sectors = response.getBody();
    assertThat(sectors).isNotNull();
    assertThat(sectors.length).isGreaterThan(0);
  }
}

