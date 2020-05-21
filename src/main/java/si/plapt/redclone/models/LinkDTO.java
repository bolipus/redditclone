package si.plapt.redclone.models;

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

  private List<CommentDTO> comments = new ArrayList<>();
}