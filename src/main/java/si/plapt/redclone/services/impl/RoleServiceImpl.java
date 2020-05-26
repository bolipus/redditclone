package si.plapt.redclone.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.entities.Role;
import si.plapt.redclone.exceptions.RoleNotFoundException;
import si.plapt.redclone.repository.RoleRepository;
import si.plapt.redclone.services.RoleService;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  public RoleServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }


  @Override
  public Role findById(Long id) throws RoleNotFoundException {
    Optional<Role> roleOptional = roleRepository.findById(id);
    if (!roleOptional.isPresent()){
      String msg = String.format("Role with id = %s not found.", id);
      log.error(msg);
      throw new RoleNotFoundException(msg);
    }
    return roleOptional.get();
  }

  @Override
  public Role findByName(String name) throws RoleNotFoundException {
    Optional<Role> roleOptional = roleRepository.findByName(name);
    if (!roleOptional.isPresent()){
      String msg = String.format("Role with name = %s not found.", name);
      log.error(msg);
      throw new RoleNotFoundException(msg);
    }
    return roleOptional.get();
  }

  @Override
  public List<Role> findAll() {
   return roleRepository.findAll();
  }
  
}