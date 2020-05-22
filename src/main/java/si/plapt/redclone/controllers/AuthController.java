package si.plapt.redclone.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import si.plapt.redclone.entities.Role;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.models.RoleDTO;
import si.plapt.redclone.models.UserDTO;
import si.plapt.redclone.services.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("api/v1/auth")
public class AuthController {

  private UserService userService;

  private ModelMapper modelMapper;

  public AuthController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @PostMapping("/register")
  public UserDTO register(@RequestBody UserDTO userDTO) {
    User user = convertEntity(userDTO);
    return convertDTO(userService.register(user));
  }

  @GetMapping("/users")
  public List<UserDTO> findAll() {
    return userService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<UserDTO> findById(@PathVariable("userId") Long userId) throws UserNotFoundException {
    User user = userService.findById(userId);
    return ResponseEntity.ok(convertDTO(user));

  }

  @GetMapping("/users/email")
  public ResponseEntity<UserDTO> findByEmail(@RequestParam(name = "email", required = true) String email)
      throws UserNotFoundException {
    UserDTO user = convertDTO(userService.findByEmail(email));
    return ResponseEntity.ok(user);
  }

  @GetMapping("/roles")
  public List<RoleDTO> findAllRoles() {
    return userService.findAllRoles().stream().map(this::convertDTO).collect(Collectors.toList());
  }


  private UserDTO convertDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  private User convertEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }

  private RoleDTO convertDTO(Role role) {
    return modelMapper.map(role, RoleDTO.class);
  }

  private Role convertEntity(RoleDTO roleDTO) {
    return modelMapper.map(roleDTO, Role.class);
  }
}