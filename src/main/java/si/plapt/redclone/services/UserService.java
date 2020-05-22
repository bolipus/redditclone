package si.plapt.redclone.services;

import java.util.List;

import si.plapt.redclone.entities.Role;
import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.UserNotFoundException;

public interface UserService {

  User save(User user);

  User register(User user);

  List<User> findAll();

  User findById(long userId) throws UserNotFoundException;
  User findByEmail(String email) throws UserNotFoundException;

  List<Role> findAllRoles();
  
}