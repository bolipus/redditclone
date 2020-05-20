package si.plapt.redclone.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Vote {
  
  @Id
  @SequenceGenerator(name="VOTE_SEQ", allocationSize=25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VOTE_SEQ" )
  private long id;

  @NonNull
  @ManyToOne
  private Link link;

  @NonNull
  private Integer vote;

}