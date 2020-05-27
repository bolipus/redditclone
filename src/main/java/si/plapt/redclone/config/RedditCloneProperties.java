package si.plapt.redclone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;


@ConfigurationProperties(prefix = "redclone" )
@Data
public class RedditCloneProperties {
    
  private String jwtSecret;

  private String jwtTokenUri;

  private Integer jwtTokenValidyDays;

}