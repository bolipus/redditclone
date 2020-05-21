package si.plapt.redclone.services;

import java.util.List;

import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.exceptions.RedCloneException;

public interface LinkService {
  
  List<Link> findAll();

  List<Comment> getComments(long linkId) throws RedCloneException;

  Link findById(Long linkId) throws RedCloneException;

  Link saveLink(Link link);

  void addComment(Long linkId, Comment comment) throws RedCloneException;


}