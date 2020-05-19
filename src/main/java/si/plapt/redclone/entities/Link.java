package si.plapt.redclone.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Link extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NonNull
  private String title;

  @NonNull
  private String url;

  @OneToMany(mappedBy = "link")
  private List<Comment> comments = new ArrayList<>();
  
}