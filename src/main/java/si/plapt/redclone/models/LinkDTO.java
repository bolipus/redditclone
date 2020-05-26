package si.plapt.redclone.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {
  private long id;
  private String title;
  private String url;

  private Integer voteCount=0;

  private String domainName;

  private String createdBy;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;

  private String prettyCreatedDate;

  private String prettyModifiedDate;

  private List<CommentDTO> comments = new ArrayList<>();
}