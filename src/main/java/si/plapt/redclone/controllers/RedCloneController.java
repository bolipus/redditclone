package si.plapt.redclone.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import si.plapt.redclone.entities.Comment.CommentDTO;
import si.plapt.redclone.entities.Link.LinkDTO;
import si.plapt.redclone.exceptions.RedCloneException;
import si.plapt.redclone.services.RedCloneService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("api/v1")
public class RedCloneController {

  private RedCloneService redCloneService;

  @Autowired
  public RedCloneController(RedCloneService redCloneService) {
    this.redCloneService = redCloneService;
  }

  @GetMapping("/links")
  public ResponseEntity<List<LinkDTO>> getAllLinks() {
    List<LinkDTO> links = redCloneService.getAllLinks();
    return ResponseEntity.ok(links);
  }

  @GetMapping("/links/{linkId}/comments")
  public ResponseEntity<List<CommentDTO>> getCommentsForLink(@PathVariable("linkId") Long linkId) {
    List<CommentDTO> comments;
    try {
      comments = redCloneService.getCommentsForLink(linkId);
    } catch (RedCloneException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return ResponseEntity.ok(comments);
  }
}