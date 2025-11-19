package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.DataJwtTokenDTO;
import com.rokaly.sgm.dto.LoginDTO;
import com.rokaly.sgm.model.User;
import com.rokaly.sgm.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class AutheticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DataJwtTokenDTO> login(@RequestBody @Valid LoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);
        var jwtToken = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataJwtTokenDTO(jwtToken));
    }
}
