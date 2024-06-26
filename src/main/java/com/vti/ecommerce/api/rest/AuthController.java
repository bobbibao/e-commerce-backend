package com.vti.ecommerce.api.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.ecommerce.config.jwt.TokenProvider;
import com.vti.ecommerce.services.IUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        System.out.println("Authentication2");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication.getName());
        String userID = userService.getUserID(loginRequest.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("id", userID);
        return ResponseEntity.ok(response);
    }

    // @PostMapping("/google")
    // public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> request) {
    //     String tokenId = request.get("tokenId");

    //     // Gửi token đến Google để xác thực
    //     GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
    //             .setAudience(Collections.singletonList("YOUR_GOOGLE_CLIENT_ID"))
    //             .build();

    //     GoogleIdToken idToken;
    //     try {
    //         idToken = verifier.verify(tokenId);
    //         if (idToken != null) {
    //             Payload payload = idToken.getPayload();
    //             String userId = payload.getSubject();
    //             String email = payload.getEmail();
    //             boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
    //             String name = (String) payload.get("name");
    //             String pictureUrl = (String) payload.get("picture");

    //             // Xử lý đăng ký hoặc đăng nhập người dùng ở đây
    //             return ResponseEntity.ok("Login successful!");
    //         } else {
    //             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    //         }
    //     } catch (GeneralSecurityException | IOException e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying token");
    //     }
    // }
}

class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
