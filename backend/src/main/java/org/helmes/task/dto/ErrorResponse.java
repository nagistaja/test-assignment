package org.helmes.task.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {
  private final int status;
  private final String error;
  private final String message;

  @JsonCreator
  public ErrorResponse(
          @JsonProperty("status") int status,
          @JsonProperty("error") String error,
          @JsonProperty("message") String message) {
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public ErrorResponse(org.springframework.http.HttpStatus status, String message) {
    this(status.value(), status.getReasonPhrase(), message);
  }
}
