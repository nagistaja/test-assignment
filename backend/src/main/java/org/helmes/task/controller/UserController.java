package org.helmes.task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.helmes.task.dto.ErrorResponse;
import org.helmes.task.dto.UserDto;
import org.helmes.task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoint for managing users")
public class UserController {

  private final UserService userService;

  @Operation(summary = "Save user", description = "Saves a new user or updates an existing one")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User saved successfully"),
          @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PostMapping("/save")
  public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errors = bindingResult.getFieldErrors()
              .stream()
              .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
              .collect(Collectors.joining("; "));
      return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, errors));
    }
    UUID editToken = userService.saveOrUpdateUser(userDto);
    return ResponseEntity.ok().body(Collections.singletonMap("editToken", editToken.toString()));
  }

  @Operation(summary = "Get user by token", description = "Returns a user by edit token")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User returned successfully"),
          @ApiResponse(responseCode = "404", description = "User not found")
  })
  @GetMapping("/edit/{token}")
  public ResponseEntity<UserDto> getUserByToken(
          @Parameter(description = "The edit token of the user")
          @PathVariable UUID token) {
    UserDto userDto = userService.getUserByToken(token);
    return ResponseEntity.ok(userDto);
  }

  @Operation(summary = "Update user", description = "Updates an existing user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "User updated successfully"),
          @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PutMapping("/update/{token}")
  public ResponseEntity<?> updateUser(
          @Parameter(description = "The edit token of the user")
          @PathVariable String token,
          @Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      String errors = bindingResult.getFieldErrors()
              .stream()
              .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
              .collect(Collectors.joining("; "));
      return ResponseEntity.badRequest().body(errors);
    }
    userService.updateUser(token, userDto);
    return ResponseEntity.ok().body("{\"message\":\"Update successful\"}");
  }
}
