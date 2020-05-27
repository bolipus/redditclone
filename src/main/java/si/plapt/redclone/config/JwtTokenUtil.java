package si.plapt.redclone.config;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtTokenUtil implements Serializable {

  private static final long serialVersionUID = -4092477073402665568L;

  private RedditCloneProperties prop;

  public JwtTokenUtil(RedditCloneProperties prop) {
    this.prop = prop;
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    String token =  doGenerateToken(claims, userDetails.getUsername());
    log.info(token);
    log.info(getUserNameFromToken(token));
    return token;
  }

  public String doGenerateToken(Map<String, Object> claims, String subject) {
    Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    Date expDate = Date
        .from(LocalDateTime.now().plusHours(prop.getJwtTokenValidyDays()).atZone(ZoneId.systemDefault()).toInstant());

    return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expDate).setSubject(subject)
        .signWith(SignatureAlgorithm.HS512, prop.getJwtSecret()).compact();
  }

  public String getUserNameFromToken(String token){
    return getAllClaimsFromToken(token).getSubject();
  }

  public Date getIssuedAtDateFromToken(String token) {
    return getAllClaimsFromToken(token).getIssuedAt();
  }

  public Date getExpirationDate(String token) {
    return getAllClaimsFromToken(token).getExpiration();
  }

  private Claims getAllClaimsFromToken(String token) {
    JwtParser parser = Jwts.parser().setSigningKey(prop.getJwtSecret());
    return parser.parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    return getExpirationDate(token).after(now);
  }

  public boolean isValidToken(String token, UserDetails userDetails) {
    String username = getUserNameFromToken(token);
    return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
  }

}