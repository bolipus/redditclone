package si.plapt.redclone.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = "users")
public class Role {

  @Id
  @SequenceGenerator(name="ROLE_SEQ", allocationSize=25, initialValue = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ" )
  private Long id;

  @NonNull
  private String name;

  @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
  private List<User> users = new ArrayList<>();
  
  public void addUser(User user){
    users.add(user);    
  }
}