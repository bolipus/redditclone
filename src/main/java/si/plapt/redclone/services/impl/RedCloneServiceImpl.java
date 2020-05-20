package si.plapt.redclone.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.entities.Comment.CommentDTO;
import si.plapt.redclone.entities.Link.LinkDTO;
import si.plapt.redclone.exceptions.RedCloneException;
import si.plapt.redclone.repository.CommentRepository;
import si.plapt.redclone.repository.LinkRepository;
import si.plapt.redclone.repository.VoteRepository;
import si.plapt.redclone.services.RedCloneService;

@Service
@Log4j2
public class RedCloneServiceImpl implements RedCloneService {

  private LinkRepository linkRepository;

  //private CommentRepository commentRepository;

//  private VoteRepository voteRepository;

  
 @Autowired
  public RedCloneServiceImpl(LinkRepository linkRepository, CommentRepository commentRepository,
      VoteRepository voteRepository) {
    this.linkRepository = linkRepository;
    //this.commentRepository = commentRepository;
    //this.voteRepository = voteRepository;
  }


  @Override
  public List<LinkDTO> getAllLinks() {
    return linkRepository.findAll().stream()
        .map(Link::getDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<CommentDTO> getCommentsForLink(long linkId) throws RedCloneException {
    Optional<Link> linkOptional = linkRepository.findById(linkId);
    if (!linkOptional.isPresent()){
      String msg = String.format("Link with id = %d not found.", linkId);
      log.error(msg);
      throw new RedCloneException(msg); 
    } 
    return linkOptional.get().getComments().stream()
        .map(Comment::getDTO)
        .collect(Collectors.toList());
  }

  @Override
  public Long getVotesForLink(long linkId) {
    
    return null;
  }

  
}