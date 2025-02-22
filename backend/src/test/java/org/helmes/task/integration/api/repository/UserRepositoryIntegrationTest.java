package org.helmes.task.integration.api.repository;

import org.helmes.task.integration.BaseIntegrationTest;
import org.helmes.task.model.Sector;
import org.helmes.task.model.User;
import org.helmes.task.repository.SectorRepository;
import org.helmes.task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Set;
import java.util.UUID;

public class UserRepositoryIntegrationTest extends BaseIntegrationTest {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SectorRepository sectorRepository;

  @Test
  public void testSaveAndFindUserWithSectors() {
    Sector sector1 = new Sector();
    sector1.setName("Sector1");
    Sector sector2 = new Sector();
    sector2.setName("Sector2");
    sector1 = sectorRepository.save(sector1);
    sector2 = sectorRepository.save(sector2);

    User user = new User();
    user.setName("Integration Test User");
    user.setAgreeToTerms(true);
    user.setEditToken(UUID.randomUUID());
    user.setSectors(Set.of(sector1, sector2));

    userRepository.save(user);

    User found = userRepository.findByEditToken(user.getEditToken()).orElse(null);
    assertThat(found).isNotNull();
    assertThat(found.getName()).isEqualTo("Integration Test User");
    assertThat(found.getSectors()).hasSize(2);
  }

  @Test
  public void testFindByEditToken_InvalidTokenReturnsEmpty() {
    UUID randomToken = UUID.randomUUID();
    assertThat(userRepository.findByEditToken(randomToken)).isEmpty();
  }
}
