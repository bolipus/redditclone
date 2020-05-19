package si.plapt.redclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import si.plapt.redclone.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
  
}