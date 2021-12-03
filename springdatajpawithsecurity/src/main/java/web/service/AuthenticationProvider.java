package web.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dto.UserDto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userDetailService")
public class AuthenticationProvider implements UserDetailsService {

    private final UserService service;

    public AuthenticationProvider(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserDto user = service.findUserByEmail(s);

            if (user == null) {
                throw new UsernameNotFoundException("Can't find user with username: \"" + s + "\"");
            }
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            if (user.getRole() != null && user.getRole().getPermissions() != null)
                authorities = user.getRole().getPermissions().stream().
                        map(SimpleGrantedAuthority::new).
                        collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
