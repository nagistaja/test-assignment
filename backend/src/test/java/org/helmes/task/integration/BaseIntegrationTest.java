package org.helmes.task.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(parallel = true)
@SuppressWarnings("resource")
public abstract class BaseIntegrationTest {

  @Container
  public static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
          new PostgreSQLContainer<>("postgres:15-alpine")
                  .withDatabaseName("helmesdb")
                  .withUsername("helmesuser")
                  .withPassword("secret");

  @DynamicPropertySource
  static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
    registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);;
  }
}
