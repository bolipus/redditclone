package si.plapt.redclone.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.exceptions.RedCloneException;
import si.plapt.redclone.repository.LinkRepository;
import si.plapt.redclone.services.LinkService;

@Service
@Log4j2
public class LinkServiceImpl implements LinkService {

  private LinkRepository linkRepository;

  public LinkServiceImpl(LinkRepository linkRepository) {
    this.linkRepository = linkRepository;
  }

  @Override
  public List<Link> findAll() {
    return linkRepository.findAll();
  }

  @Override
  public Link findById(Long linkId) throws RedCloneException {
    Optional<Link> linkOptional = linkRepository.findById(linkId);
    if (!linkOptional.isPresent()){
      String msg = String.format("Link with id = %d not found.", linkId);
      log.error(msg);
      throw new RedCloneException(msg); 
    } 
    return linkOptional.get();
  }

  @Override
  public List<Comment> getComments(long linkId) throws RedCloneException {     
    return findById(linkId).getComments();
  }

  @Override
  @Transactional
  public Link saveLink(Link link) {
    return linkRepository.save(link);
  }

  @Override
  public void addComment(Long linkId, Comment comment) throws RedCloneException{
    Optional<Link> linkOptional = linkRepository.findById(linkId);
    if (!linkOptional.isPresent()){
      String msg = String.format("Link with id = %d not found.", linkId);
      log.error(msg);
      throw new RedCloneException(msg); 
    } 
    Link link = linkOptional.get();
    link.addComment(comment);
    linkRepository.save(link);
  }

  

}