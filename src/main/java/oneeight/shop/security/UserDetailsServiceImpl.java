package oneeight.shop.security;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser =  appUserRepository.findByLogin(username).orElseThrow(()->{
            String message = "No user found with specified 'login' (%s)".formatted(username);
            throw new UsernameNotFoundException(message);
        });
        return User
                .withUsername(appUser.getLogin())
                .password(appUser.getPassword())
                .authorities("ROLE_"+appUser.getRole().getServiceName())
                .build();
    }
}
