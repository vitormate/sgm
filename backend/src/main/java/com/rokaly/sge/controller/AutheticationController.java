package com.rokaly.sge.controller;

import com.rokaly.sge.dto.DataJwtTokenDTO;
import com.rokaly.sge.dto.LoginDTO;
import com.rokaly.sge.model.User;
import com.rokaly.sge.security.TokenService;
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
    public ResponseEntity<DataJwtTokenDTO> login(@RequestBody LoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);
        var jwtToken = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataJwtTokenDTO(jwtToken));
    }
}
