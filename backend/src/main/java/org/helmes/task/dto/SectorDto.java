package org.helmes.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Represents a sector with optional children for hierarchical structures.")
public class SectorDto {

  @Schema(description = "Unique identifier for the sector.", example = "1")
  private Long id;

  @Schema(description = "Descriptive name of the sector.", example = "Manufacturing")
  private String name;

  @Schema(description = "Optional list of child sectors.")
  private List<SectorDto> children;
}

