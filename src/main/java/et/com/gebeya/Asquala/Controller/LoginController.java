package et.com.gebeya.Asquala.Controller;

import et.com.gebeya.Asquala.Dto.AuthenticationRequest;
import et.com.gebeya.Asquala.Dto.AuthenticationResponse;
import et.com.gebeya.Asquala.Dto.RegisterRequest;
import et.com.gebeya.Asquala.Services.AuthenticationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Api/v1/Login")
@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private AuthenticationServices authenticationServices;


    @PostMapping("log-in")
    public ResponseEntity<AuthenticationResponse> authonticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationServices.login(request));
    }
}


