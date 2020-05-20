package si.plapt.redclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import si.plapt.redclone.entities.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}