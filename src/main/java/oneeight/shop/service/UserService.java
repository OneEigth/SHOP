package oneeight.shop.service;

import lombok.RequiredArgsConstructor;
import oneeight.shop.entity.AppUser;
import oneeight.shop.entity.Role;
import oneeight.shop.entity.User;
import oneeight.shop.exception.RecordNotFoundException;
import oneeight.shop.repository.AppUserRepository;
import oneeight.shop.repository.RoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final AppUserRepository userRepository;

    private final RoleRepository roleRepository;




    public void registerUser(AppUser appUser) {
        appUser.setRole(getRole("user"));
        String encodedPassword = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUser.setDateReg(LocalDateTime.now());
        userRepository.save(appUser);
    }

    public Role getRole(String serviceName) {
        return roleRepository.findByServiceName(serviceName).orElseThrow(() -> {
            String message = "'Role' not found by 'serviceName': %s".formatted(serviceName);
            throw new RecordNotFoundException(message);
        });
    }

    public AppUser getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return userRepository.findByLogin(authentication.getName()).orElse(null);
    }

    public Boolean isAdmin() {
        Boolean isAdmin = false;
        AppUser appUser = getCurrentUser();
        if (appUser.getRole().getServiceName().equals("admin")) {
            isAdmin = true;
        }
        return isAdmin;
    }

    public void setAdminRole(AppUser appUser){

        appUser.setRole(getRole("admin"));
        System.out.println(appUser.getRole().getServiceName());
        userRepository.save(appUser);
    }

    public List<AppUser> allUsers (){
        return userRepository.findAll();
    }

    public void deleteUser(AppUser appUser){
        userRepository.delete(appUser);

    }
}
