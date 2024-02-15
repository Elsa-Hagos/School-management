package et.com.gebeya.Asquala.Services;

import et.com.gebeya.Asquala.Dto.AuthenticationRequest;
import et.com.gebeya.Asquala.Dto.AuthenticationResponse;
import et.com.gebeya.Asquala.Dto.RegisterRequest;
//import et.com.gebeya.Asquala.Enum.Roles;
import et.com.gebeya.Asquala.Model.Roles;
import et.com.gebeya.Asquala.Model.Users;
import et.com.gebeya.Asquala.Repo.UsersRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class AuthenticationServices {
    @Autowired
    public UsersRepo usersRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public JwtService jwtService;
    @Autowired
    public AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(Roles.USER)
                .build();
        usersRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );
        var user = usersRepo.findByusername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

