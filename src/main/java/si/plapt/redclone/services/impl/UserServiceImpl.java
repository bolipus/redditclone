package si.plapt.redclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.entities.Role;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.repository.RoleRepository;
import si.plapt.redclone.repository.UserRepository;
import si.plapt.redclone.services.RoleService;
import si.plapt.redclone.services.UserService;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final RoleService roleService;
  
  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
    this.userRepository = userRepository;
    this.roleService = roleService;
  }

  public User save(User user){
    return userRepository.save(user);
  }

  @Override
  public User register(User user) throws RoleNotFoundException {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String password = "{bcrypt}" + encoder.encode(user.getPassword());
    user.setEnabled(false);
    user.setPassword(password);
    user.setConfirmedPassword(password);
    Role role = roleService.findByName("ROLE_USER");
    user.addRole(role);
   
    user.setConfirmationCode(UUID.randomUUID().toString());

    user = save(user);

    sendActivationMail(user);
    return user;
  }


  private void sendActivationMail(User user) {
    
  }

  @Override
  public User findById(long userId) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(userId);
    if (!userOptional.isPresent()) {
      String msg = String.format("User with id = %d not found.", userId);
      log.error(msg);
      throw new UserNotFoundException(msg);
    }

    return userOptional.get();
  }

  @Override
  public User findByEmail(String email) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (!userOptional.isPresent()) {
      String msg = String.format("User with email = %d not found.", email);
      log.error(msg);
      throw new UserNotFoundException(msg);
    }

    return userOptional.get();
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {

    

    Optional<User> userOptional = userRepository.findByEmail(email);
    if (!userOptional.isPresent()) {
      String msg = String.format("User not found.");
      log.error(msg);
      throw new UserNotFoundException(msg);
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    log.info("Password:" + password);
    log.info("User Enc. pass.:" + userOptional.get().getPassword());

    if (!encoder.matches(password, userOptional.get().getPassword().replace("{bcrypt}",""))) {
      String msg = String.format("Passwords does not match.");
      log.error(msg);
      throw new UserNotFoundException(msg);
    }

    return userOptional.get();
  }

  
  
  
}