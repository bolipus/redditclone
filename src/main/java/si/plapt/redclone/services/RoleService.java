package si.plapt.redclone.services;

import java.util.List;

import si.plapt.redclone.entities.Role;
import si.plapt.redclone.exceptions.RoleNotFoundException;

public interface RoleService {
   Role findById(Long id) throws RoleNotFoundException;
   Role findByName(String name) throws RoleNotFoundException;
   List<Role> findAll();
}