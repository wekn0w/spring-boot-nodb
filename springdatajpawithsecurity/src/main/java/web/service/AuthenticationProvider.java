package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailService")
public class AuthenticationProvider implements UserDetailsService {

    private final UserService service;

    @Autowired
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
            List<GrantedAuthority> authorities = new ArrayList();
            if (user.getRole() != null)
                authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
