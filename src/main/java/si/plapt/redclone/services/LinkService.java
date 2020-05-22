package si.plapt.redclone.services;

import java.util.List;

import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.exceptions.LinkNotFoundException;
import si.plapt.redclone.exceptions.RedCloneException;

public interface LinkService {
  
  List<Link> findAll();

  List<Comment> getComments(long linkId) throws LinkNotFoundException;

  Link findById(Long linkId) throws LinkNotFoundException;

  Link saveLink(Link link);

  void addComment(Long linkId, Comment comment) throws LinkNotFoundException;

  Integer vote(long linkId, short direction) throws LinkNotFoundException;

}