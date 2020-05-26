package si.plapt.redclone.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.netty.handler.codec.base64.Base64;
import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.entities.Role;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.models.RoleDTO;
import si.plapt.redclone.models.UserCredentialsDTO;
import si.plapt.redclone.models.UserDTO;
import si.plapt.redclone.services.RoleService;
import si.plapt.redclone.services.UserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@Log4j2
public class AuthController {

  private UserService userService;

  private ModelMapper modelMapper;

  public AuthController(UserService userService,ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }
  
  @RequestMapping("/login")
  public UserDTO login(@RequestBody UserCredentialsDTO userCredentials) throws UserNotFoundException {
    log.info("User credentials:" + userCredentials);
    return convertDTO(userService.findByEmailAndPassword(userCredentials.getEmail(), userCredentials.getPassword()));
  }

  @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64Utils.decode(authToken.getBytes())).split(":")[0];
    }

    private UserDTO convertDTO(User user) {
      return modelMapper.map(user, UserDTO.class);
    }
}