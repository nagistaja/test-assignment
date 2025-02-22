package org.helmes.task.unit.controller;

import org.helmes.task.controller.UserController;
import org.helmes.task.dto.UserDto;
import org.helmes.task.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock
  private UserService userService;

  @Mock
  private BindingResult bindingResult;

  @InjectMocks
  private UserController userController;

  @Test
  void saveUser_ValidUser_ReturnsOkWithEditToken() {
    UserDto userDto = new UserDto();
    UUID editToken = UUID.randomUUID();
    when(bindingResult.hasErrors()).thenReturn(false);
    when(userService.saveOrUpdateUser(userDto)).thenReturn(editToken);

    ResponseEntity<?> response = userController.saveUser(userDto, bindingResult);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Collections.singletonMap("editToken", editToken.toString()), response.getBody());
  }

  @Test
  void saveUser_InvalidUser_ReturnsBadRequestWithErrors() {
    UserDto userDto = new UserDto();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getFieldErrors()).thenReturn(Collections.emptyList());

    ResponseEntity<?> response = userController.saveUser(userDto, bindingResult);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void getUserByToken_ValidToken_ReturnsUserDto() {
    UUID token = UUID.randomUUID();
    UserDto userDto = new UserDto();
    when(userService.getUserByToken(token)).thenReturn(userDto);

    ResponseEntity<UserDto> response = userController.getUserByToken(token);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userDto, response.getBody());
  }

  @Test
  void updateUser_ValidUser_ReturnsOkWithMessage() {
    String token = UUID.randomUUID().toString();
    UserDto userDto = new UserDto();
    when(bindingResult.hasErrors()).thenReturn(false);

    ResponseEntity<?> response = userController.updateUser(token, userDto, bindingResult);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("{\"message\":\"Update successful\"}", response.getBody());
  }

  @Test
  void updateUser_InvalidUser_ReturnsBadRequestWithErrors() {
    String token = UUID.randomUUID().toString();
    UserDto userDto = new UserDto();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getFieldErrors()).thenReturn(Collections.emptyList());

    ResponseEntity<?> response = userController.updateUser(token, userDto, bindingResult);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}
