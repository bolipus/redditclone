package si.plapt.redclone.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class Auditable {

  public static PrettyTime prettyTime = new PrettyTime();
  static {
    prettyTime.setLocale(new Locale("sl_si"));
  }

  @CreatedDate
  private LocalDateTime createdDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private LocalDateTime modifiedDate;

  @LastModifiedBy
  private String modifiedBy;

  public String getPrettyCreatedDate(){
    Date date = Date.from(getCreatedDate().atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
  } 

  public String getPrettyModifiedDate(){
    Date date = Date.from(getCreatedDate().atZone(ZoneId.systemDefault()).toInstant());
    return prettyTime.format(date);
  }
}