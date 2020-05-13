package si.plapt.redclone.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Comment {
  
  @Id
  @GeneratedValue
  private long id;

  @NonNull
  private String body;

  @NonNull
  private User user;

  @ManyToOne
  private Link link; 

}