package si.plapt.redclone.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import si.plapt.redclone.entities.User;

public class AuditorAwareImpl implements AuditorAware<String> {

  @Override
  public Optional<String> getCurrentAuditor() {

    if (SecurityContextHolder.getContext().getAuthentication() == null){
      return Optional.of("admin@gmail.com"); //hardcoded
    }

    String user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail();
    return Optional.of(user);
  }
  
}