package org.helmes.task.integration.performance;

import org.helmes.task.dto.SectorDto;
import org.helmes.task.integration.BaseIntegrationTest;
import org.helmes.task.service.SectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class SectorLoadTest extends BaseIntegrationTest {

  @Autowired
  private SectorService service;

  @Test
  void loadLargeHierarchy_PerformsAcceptably() {
    long start = System.currentTimeMillis();

    List<SectorDto> result = service.getAllSectorsFlat();

    long duration = System.currentTimeMillis() - start;
    assertThat(duration).isLessThan(200);
    assertThat(result).hasSizeGreaterThan(50);
  }
}
