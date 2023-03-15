package oneeight.shop.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests((authorizationConfigurer) -> {
            authorizationConfigurer.antMatchers("/admin/**").hasAnyRole("admin");
            authorizationConfigurer.antMatchers("/user/**").hasAnyRole("user");
            authorizationConfigurer.antMatchers("/registration").not().authenticated();
            authorizationConfigurer.antMatchers("/repository_test/first_resource").authenticated();
            authorizationConfigurer.antMatchers("/cart/**").authenticated();
            authorizationConfigurer.anyRequest().permitAll();
        });
        http.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.defaultSuccessUrl("/filter");
            formLoginConfigurer.loginPage("/login");
            formLoginConfigurer.permitAll();



        });
       http.logout().logoutSuccessUrl("/login");
        return http.build();
    }
}
