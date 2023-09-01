package pessoal.estudos.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import pessoal.estudos.spring.entity.UserEntity;
import pessoal.estudos.spring.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class AuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

  @Autowired
  private UserRepository userRepository;

  public AuthenticationFilter(UserRepository userRepository) {
    this.setAuthenticationManager(buildAuthenticationManager(userRepository));
  }

  @Override
  protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
    return request.getHeader("KEY");
  }

  @Override
  protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
    return "N/A";
  }

  private static AuthenticationManager buildAuthenticationManager(UserRepository userRepository) {

    return authentication -> {
      String userToken = (String) authentication.getPrincipal();
      Optional<UserEntity> userOptional = userRepository.findById(userToken);
      if (userOptional.isPresent()) {
        UserEntity user = userOptional.get();
        String authority = String.valueOf(user.getUserRole());
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(authority));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), "", authorities);
        return new PreAuthenticatedAuthenticationToken(userDetails, null, authorities);
      }
      authentication.setAuthenticated(false);
      return authentication;
    };
  }
}