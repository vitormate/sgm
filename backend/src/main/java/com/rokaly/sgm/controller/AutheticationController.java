package com.rokaly.sgm.controller;

import com.rokaly.sgm.dto.DataJwtTokenDTO;
import com.rokaly.sgm.dto.LoginDTO;
import com.rokaly.sgm.dto.RegisterDTO;
import com.rokaly.sgm.model.User;
import com.rokaly.sgm.repository.UserRepository;
import com.rokaly.sgm.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AutheticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<DataJwtTokenDTO> login(@RequestBody @Valid LoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authToken);
        var jwtToken = tokenService.getToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DataJwtTokenDTO(jwtToken));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data) {
        if (userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
