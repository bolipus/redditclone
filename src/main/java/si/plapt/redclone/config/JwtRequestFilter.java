package si.plapt.redclone.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private UserDetailsService userDetailsService;
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenUtil = jwtTokenUtil;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUserNameFromToken(jwtToken);
      } catch (IllegalArgumentException e) {
        // throw new ServletException("Unable to get JWT exception.");
      } catch (ExpiredJwtException e) {
        // throw new ServletException("Jwt token has expired.");
      }

    } else {
      // throw new ServletException("JWT token does not begin with Beaerer string");
    }

    if (username != null && (SecurityContextHolder.getContext().getAuthentication() == null
        || SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (!jwtTokenUtil.isValidToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails,
            null, userDetails.getAuthorities());
        userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);

      }
    }

    filterChain.doFilter(request, response);

  }

}