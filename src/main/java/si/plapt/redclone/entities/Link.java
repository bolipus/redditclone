package si.plapt.redclone.entities;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString(exclude = { "comments", "votes" })
public class Link extends Auditable {

  @Id
  @SequenceGenerator(name = "LINK_SEQ", allocationSize = 25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LINK_SEQ")
  private long id;

  @NonNull
  private String title;

  @NonNull
  private String url;

  @OneToMany(mappedBy = "link", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @ManyToOne
  private User user;

  @OneToMany(mappedBy = "link")
  private List<Vote> votes = new ArrayList<>();

  private Integer voteCount = 0;

  public String getDomainName() throws URISyntaxException {
    URI uri = new URI(this.url);
    String domain = uri.getHost();
    return  domain.startsWith("www.*")? domain.substring(4) : domain;
  }

  public void addComment(Comment comment){
    comments.add(comment);
    comment.setLink(this);
  }
  public void removeComment(Comment comment){
    comments.remove(comment);
    comment.setLink(null);
  }
}