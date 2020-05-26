package si.plapt.redclone.services;

import java.util.List;


import si.plapt.redclone.entities.User;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.exceptions.UserNotFoundException;

public interface UserService {

  User save(User user);

  User register(User user) throws RoleNotFoundException;

  List<User> findAll();

  User findById(long userId) throws UserNotFoundException;
  User findByEmail(String email) throws UserNotFoundException;

  User findByEmailAndPassword(String email, String password) throws UserNotFoundException;
  
}