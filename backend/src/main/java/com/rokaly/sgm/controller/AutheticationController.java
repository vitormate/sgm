package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.DataJwtTokenDTO;
import com.rokaly.sgm.dto.LoginDTO;
import com.rokaly.sgm.dto.RegisterDTO;
import com.rokaly.sgm.service.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutheticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<DataJwtTokenDTO> login(@RequestBody @Valid LoginDTO data) {
        return authenticationService.loginService(data);
    }

    @SecurityRequirement(name = "bearer-key")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        return authenticationService.registerService(data);
    }
}
