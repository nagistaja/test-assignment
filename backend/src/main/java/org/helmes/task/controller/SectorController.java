package org.helmes.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.helmes.task.dto.SectorDto;
import org.helmes.task.service.SectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
@Tag(name = "Sectors", description = "Endpoint for managing sectors")
public class SectorController {

  private final SectorService sectorService;

  @Operation(summary = "Get hierarchical sectors", description = "Returns sectors organized in a hierarchical structure")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "List of sectors returned successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/hierarchical")
  public ResponseEntity<List<SectorDto>> getAllSectorsHierarchical() {
    List<SectorDto> sectors = sectorService.getAllSectorsHierarchical();
    return ResponseEntity.ok(sectors);
  }

  @Operation(summary = "Get flat sectors", description = "Returns a flat list of sectors")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "List of sectors returned successfully"),
          @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/flat")
  public ResponseEntity<List<SectorDto>> getAllSectorsFlat() {
    List<SectorDto> sectors = sectorService.getAllSectorsFlat();
    return ResponseEntity.ok(sectors);
  }
}
