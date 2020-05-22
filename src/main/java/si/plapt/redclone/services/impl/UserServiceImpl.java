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
import si.plapt.redclone.exceptions.UserNotFoundException;
import si.plapt.redclone.repository.RoleRepository;
import si.plapt.redclone.repository.UserRepository;
import si.plapt.redclone.services.UserService;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private RoleRepository roleRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public User save(User user){
    return userRepository.save(user);
  }

  @Override
  public User register(User user) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String password = "{bcrypt}" + encoder.encode(user.getPassword());
    user.setEnabled(false);
    user.setPassword(password);
    user.setConfirmedPassword(password);
    Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
    if (roleOptional.isPresent()){
      user.addRole(roleOptional.get());
    }
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
  public List<Role> findAllRoles() {
    return roleRepository.findAll();
  }
  
  
}