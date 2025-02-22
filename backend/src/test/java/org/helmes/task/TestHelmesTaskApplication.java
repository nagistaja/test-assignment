package org.helmes.task;

import org.springframework.boot.SpringApplication;

public class TestHelmesTaskApplication {

  public static void main(String[] args) {
    SpringApplication.from(HelmesTaskApplication::main).run(args);
  }

}
