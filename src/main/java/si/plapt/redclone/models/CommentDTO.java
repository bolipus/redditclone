package si.plapt.redclone.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

  private long id;

  private String body;

  private String createdBy;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;

  private String prettyCreatedDate;

  private String prettyModifiedDate;

}