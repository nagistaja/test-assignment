package org.helmes.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description="Represents a user form submission/request.")
public class UserDto {

  @Schema(description="User's full name", example="John Doe")
  @NotBlank(message = "Name is mandatory")
  private String name;

  @Schema(description="Selected sector IDs for this user", example="[1,4]")
  @NotEmpty(message = "Select at least one sector")
  private List<Long> sectorIds;

  @Schema(description="Whether the user agrees to terms", example="true")
  @AssertTrue(message = "You must agree to terms")
  private Boolean agreeToTerms;

  @Schema(description="Edit token for this user", example="123e4567-e89b-12d3-a456-426614174000")
  private UUID editToken;

  public UserDto(String name, List<Long> sectorIds, Boolean agreeToTerms) {
    this.name = name;
    this.sectorIds = sectorIds;
    this.agreeToTerms = agreeToTerms;
  }
}
