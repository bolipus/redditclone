package si.plapt.redclone.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

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
  @GeneratedValue
  private long id;

  @NonNull
  private User user;

  @NonNull
  private Link link;

  @NonNull
  private Integer vote;

}