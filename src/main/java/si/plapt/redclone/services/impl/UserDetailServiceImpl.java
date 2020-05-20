package si.plapt.redclone.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import si.plapt.redclone.entities.User;
import si.plapt.redclone.repository.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailServiceImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findByEmail(email);
    if (!userOptional.isPresent()){
      throw new UsernameNotFoundException("Cannot find user with username:" + email);
      
    } 
    
    return userOptional.get();
  }
  
}