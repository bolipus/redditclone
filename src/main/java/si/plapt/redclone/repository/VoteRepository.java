package si.plapt.redclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.plapt.redclone.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
  
}