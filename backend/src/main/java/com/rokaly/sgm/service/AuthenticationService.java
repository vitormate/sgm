package com.rokaly.sgm.service;

import com.rokaly.sgm.dto.DataJwtTokenResponse;
import com.rokaly.sgm.dto.LoginRequest;
import com.rokaly.sgm.dto.RegisterRequest;
import com.rokaly.sgm.model.User;
import com.rokaly.sgm.repository.UserRepository;
import com.rokaly.sgm.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthenticationService(AuthenticationManager manager, TokenService tokenService, UserRepository userRepository) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<DataJwtTokenResponse> loginService(LoginRequest data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);
        var jwtToken = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataJwtTokenResponse(jwtToken));
    }

    public ResponseEntity<Void> registerService(RegisterRequest data) {
        if (userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
