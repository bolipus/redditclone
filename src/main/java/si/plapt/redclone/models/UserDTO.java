package si.plapt.redclone.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;

  private String email;

  private String firstName;

  private String lastName;

  private String alias;

  private String fullName;

  private Boolean enabled;

  private List<RoleDTO> roles = new ArrayList<>();
  
}