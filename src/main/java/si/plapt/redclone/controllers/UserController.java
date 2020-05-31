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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import si.plapt.redclone.entities.Role;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.models.RoleDTO;
import si.plapt.redclone.models.UserDTO;
import si.plapt.redclone.models.UserRegisterDTO;
import si.plapt.redclone.services.RoleService;
import si.plapt.redclone.services.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping(value= "api/v1", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
  
  private UserService userService;
  private RoleService roleService;

  private ModelMapper modelMapper;

  public UserController(UserService userService, RoleService roleService, ModelMapper modelMapper) {
    this.userService = userService;
    this.roleService = roleService;
    this.modelMapper = modelMapper;
  }


  @PostMapping(value= "/users/register")
  public ResponseEntity<UserDTO> register(@RequestBody UserRegisterDTO userRegister) throws RoleNotFoundException {
    User user = convertEntity(userRegister);
    return ResponseEntity.ok(convertDTO(userService.register(user)));
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
  public ResponseEntity<List<RoleDTO>> findAllRoles() {
    List<RoleDTO> roles = roleService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    return ResponseEntity.ok(roles);
  }

  @GetMapping("/roles/{roleId}")
  public ResponseEntity<RoleDTO> findRolesById(@PathVariable("roleId") Long roleId) throws RoleNotFoundException {
    Role role = roleService.findById(roleId);
    return ResponseEntity.ok(convertDTO(role));
  }

  private UserDTO convertDTO(User user) {
    return modelMapper.map(user, UserDTO.class);
  }

  private User convertEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }

  private User convertEntity(UserRegisterDTO userDTO) {
    return modelMapper.map(userDTO, User.class);
  }

  private RoleDTO convertDTO(Role role) {
    return modelMapper.map(role, RoleDTO.class);
  }

  private Role convertEntity(RoleDTO roleDTO) {
    return modelMapper.map(roleDTO, Role.class);
  }
}