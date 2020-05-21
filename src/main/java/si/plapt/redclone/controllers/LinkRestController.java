package si.plapt.redclone.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.exceptions.RedCloneException;
import si.plapt.redclone.models.CommentDTO;
import si.plapt.redclone.models.LinkDTO;
import si.plapt.redclone.services.LinkService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("api/v1/links")
public class LinkRestController {
  private LinkService linkService;

  private ModelMapper modelMapper;

  @Autowired
  public LinkRestController(LinkService linkService, ModelMapper modelMapper) {
    this.linkService = linkService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/")
  public ResponseEntity<List<LinkDTO>> getAllLinks() {
    List<LinkDTO> links = linkService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    return ResponseEntity.ok(links);
  }

  @GetMapping("/{linkId}")
  public ResponseEntity<LinkDTO> getLink(@PathVariable("linkId") Long linkId) {
    try {
      Link link = linkService.findById(linkId);
      return ResponseEntity.ok(convertDTO(link));
    } catch (RedCloneException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
  }

  @PostMapping("/")
  public ResponseEntity<LinkDTO> createLink(@RequestBody LinkDTO link) {
    Link savedlink = linkService.saveLink(convertEntity(link));
    return ResponseEntity.ok(convertDTO(savedlink));
  }

  @GetMapping("/{linkId}/comments")
  public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("linkId") Long linkId) {
    List<CommentDTO> comments;
    try {

      comments = linkService.getComments(linkId).stream().map(this::convertDTO).collect(Collectors.toList());

    } catch (RedCloneException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }
    return ResponseEntity.ok(comments);
  }

  @PostMapping("/{linkId}/comments")
  public ResponseEntity<Void> addComment(@PathVariable("linkId") Long linkId, @RequestBody CommentDTO commentDTO) {

    try {
      linkService.addComment(linkId, convertEntity(commentDTO));
    } catch (RedCloneException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }

    return ResponseEntity.ok().build();
  }

  private LinkDTO convertDTO(Link link) {
    return modelMapper.map(link, LinkDTO.class);
  }

  private CommentDTO convertDTO(Comment comment) {
    return modelMapper.map(comment, CommentDTO.class);
  }

  private Link convertEntity(LinkDTO linkDTO) {
    return modelMapper.map(linkDTO, Link.class);
  }

  private Comment convertEntity(CommentDTO commentDTO) {
    return modelMapper.map(commentDTO, Comment.class);
  }
}