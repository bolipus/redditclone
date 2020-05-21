package si.plapt.redclone.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

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
public class Comment extends Auditable {
  
  @Id
  @SequenceGenerator(name="COMMENT_SEQ", allocationSize=25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ")
  private Long id;

  @NonNull
  private String body;

  @ManyToOne(fetch = FetchType.LAZY)
  private Link link; 
}