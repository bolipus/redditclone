package si.plapt.redclone;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class AuditorAwareImpl implements AuditorAware {

  @Override
  public Optional<String> getCurrentAuditor() {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return Optional.of(user.getUsername());
  }
  
}