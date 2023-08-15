package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.user.LoginRecord;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokenDataJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
  
  @Autowired
  private AuthenticationManager manager;

  @Autowired
  private TokenService tokenService;

  @PostMapping
  public ResponseEntity<TokenDataJWT> doLogin(@RequestBody @Valid LoginRecord loginData) { 
    var authenticationToken = new UsernamePasswordAuthenticationToken(loginData.login(), loginData.password());
    var authentication = manager.authenticate(authenticationToken);
    var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());
    return ResponseEntity.ok(new TokenDataJWT(tokenJwt));
  }
}
