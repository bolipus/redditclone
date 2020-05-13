package si.plapt.redclone.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NonNull
  @ManyToOne
  private Link link;

  @NonNull
  private Integer vote;

}