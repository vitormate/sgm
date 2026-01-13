package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.DataJwtTokenResponse;
import com.rokaly.sgm.dto.LoginRequest;
import com.rokaly.sgm.dto.RegisterRequest;
import com.rokaly.sgm.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AutheticationController {

    private final AuthenticationService authenticationService;

    public AutheticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<DataJwtTokenResponse> login(@RequestBody @Valid LoginRequest data) {
        return authenticationService.loginService(data);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequest data) {
        return authenticationService.registerService(data);
    }
}
