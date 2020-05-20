package si.plapt.redclone.services;

import java.util.List;

import si.plapt.redclone.entities.Comment.CommentDTO;
import si.plapt.redclone.entities.Link.LinkDTO;
import si.plapt.redclone.exceptions.RedCloneException;


public interface RedCloneService {
  
  List<LinkDTO> getAllLinks();

  List<CommentDTO> getCommentsForLink(long linkId) throws RedCloneException;

  Long getVotesForLink(long linkId);
}