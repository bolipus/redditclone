package si.plapt.redclone.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCredentialsDTO {
   private String email;
   private String password;
}