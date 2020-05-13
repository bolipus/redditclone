package si.plapt.redclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.plapt.redclone.entities.Link;


@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
  
}