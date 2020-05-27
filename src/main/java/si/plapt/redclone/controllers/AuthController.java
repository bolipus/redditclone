package si.plapt.redclone.controllers;

import java.security.Principal;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.config.JwtTokenUtil;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.models.ResponseTokenDTO;
import si.plapt.redclone.models.UserCredentialsDTO;
import si.plapt.redclone.models.UserDTO;
import si.plapt.redclone.services.UserService;
import si.plapt.redclone.services.impl.UserDetailServiceImpl;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:9090" })
@Log4j2
public class AuthController {

  private UserService userService;

  private ModelMapper modelMapper;

  private UserDetailsService userDetailService;

  private AuthenticationManager authenticationManager;

  private JwtTokenUtil jwtTokenUtil;

  @Autowired

  public AuthController(UserService userService, ModelMapper modelMapper, UserDetailsService userDetailService,
      AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
    this.userService = userService;
    this.modelMapper = modelMapper;
    this.userDetailService = userDetailService;
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<ResponseTokenDTO> createAuthenticateToken(@RequestBody UserCredentialsDTO userCredentials)
      throws AuthException {
    authenticate(userCredentials.getEmail(), userCredentials.getPassword());

    final UserDetails userDetails = userDetailService.loadUserByUsername(userCredentials.getEmail());

    final String token = jwtTokenUtil.generateToken(userDetails);



    return ResponseEntity.ok(new ResponseTokenDTO(token));
  }


  private void authenticate(String username, String password) throws AuthException {
    try {
      //Authentication auth = 
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
     // SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (DisabledException ex) {
      String msg = "User disabled";
      log.error(msg);
      throw new AuthException(msg);
    } catch (BadCredentialsException e) { 
      String msg = "Invalid credential";
      log.error(msg); 
			throw new AuthException(msg);
		}
  }

  @RequestMapping("/user")
  public Principal user(HttpServletRequest request) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth!= null) {
        return (Principal) auth.getPrincipal();
      } else {
        return null;
      }
    }

    @GetMapping("/authenticatedUser")
    public ResponseEntity<UserDTO> authenticatedUser(HttpServletRequest request) throws AuthException {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth!= null) {
        User user = (User) auth.getPrincipal();
        return ResponseEntity.ok(convertDTO(user));
      } else {
        throw new AuthException("No user is authenticated");
      }

    }

    private UserDTO convertDTO(User user) {
      return modelMapper.map(user, UserDTO.class);
    }

}