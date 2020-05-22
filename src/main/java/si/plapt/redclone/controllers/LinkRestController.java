package si.plapt.redclone.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import si.plapt.redclone.entities.Comment;
import si.plapt.redclone.entities.Link;
import si.plapt.redclone.exceptions.LinkNotFoundException;
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

  @GetMapping("")
  public ResponseEntity<List<LinkDTO>> getAllLinks() {
    List<LinkDTO> links = linkService.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    return ResponseEntity.ok(links);
  }

  @GetMapping("/{linkId}")
  public ResponseEntity<LinkDTO> getLink(@PathVariable("linkId") Long linkId) throws LinkNotFoundException {
    Link link = linkService.findById(linkId);
      return ResponseEntity.ok(convertDTO(link));
  }

  @PostMapping("")
  public ResponseEntity<LinkDTO> createLink(@RequestBody LinkDTO link) {
    Link savedlink = linkService.saveLink(convertEntity(link));
    return ResponseEntity.ok(convertDTO(savedlink));
  }

  @GetMapping("/{linkId}/comments")
  public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("linkId") Long linkId) throws LinkNotFoundException{
    List<CommentDTO> comments = linkService.getComments(linkId).stream().map(this::convertDTO).collect(Collectors.toList());;
   
    return ResponseEntity.ok(comments);
  }

  @PostMapping("/{linkId}/comments")
  public ResponseEntity<Void> addComment(@PathVariable("linkId") Long linkId, @RequestBody CommentDTO commentDTO)
      throws LinkNotFoundException {

    linkService.addComment(linkId, convertEntity(commentDTO));

    return ResponseEntity.ok().build();
  }

  @PostMapping("/{linkId}/vote")
  public ResponseEntity<Integer> vote(@PathVariable("linkId") Long linkId,
      @RequestParam(name = "direction", required = true) Short direction) throws LinkNotFoundException{
        return ResponseEntity.ok(linkService.vote(linkId, direction));
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