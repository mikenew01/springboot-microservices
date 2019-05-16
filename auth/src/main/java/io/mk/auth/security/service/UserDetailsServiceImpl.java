package io.mk.auth.security.service;


import io.mk.models.ApplicationUser;
import io.mk.repositories.ApplicationUserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(final String username){
        log.info("Searching in the database the user by username {}", username);
        ApplicationUser applicationUser = applicationUserRepository.finbByUsername(username);

        if(Objects.isNull(applicationUser)) {
            log.error("ApplicationUser is mandatory");
            throw new UsernameNotFoundException(String.format("Application user '%s' not found", username));
        }

        return new CustomUserDetails(applicationUser);
    }

    @Data
    private static final class CustomUserDetails extends ApplicationUser implements UserDetails {

        public CustomUserDetails(@NotNull ApplicationUser user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
