package et.com.gebeya.Asquala.Configuration;

import et.com.gebeya.Asquala.Filter.JwtAuthenticationFilter;
import et.com.gebeya.Asquala.Model.Roles;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    public static final String ADMIN = "ADMIN";
    public static final String TEACHER = "TEACHER";
    public static final String STUDENT = "STUDENT";
    public static final String [] UNAUTHORIZED_MATCHERS = {
            "/Api/v1/Login",




    };

    public static final String [] ADMIN_MATCHERS = {
            "/Api/v1/admin/**"
    };
    public static final String [] STUDENT_MATCHERS = {
            "/api/v1/student/**"
    };
    public static final String [] TEACHER_MATCHERS = {
            "/api/v1/teacher/**"
    };



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
               // .csrf(csrf -> csrf.disable())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
               // .authorizeRequests(auth -> auth.anyRequest().authenticated())
                .authorizeHttpRequests(request -> request.requestMatchers(UNAUTHORIZED_MATCHERS).permitAll())

                .authorizeHttpRequests(request -> request.requestMatchers(ADMIN_MATCHERS).hasAuthority(Roles.ADMIN.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(TEACHER_MATCHERS).hasAuthority(Roles.TEACHER.name()))
                .authorizeHttpRequests(request -> request.requestMatchers(STUDENT_MATCHERS).hasAuthority(Roles.STUDENT.name()))

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider);
        //  .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}


