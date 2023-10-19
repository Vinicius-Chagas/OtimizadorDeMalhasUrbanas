package upx.facens.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upx.facens.domain.login.DadosLogin;
import upx.facens.domain.login.Login;
import upx.facens.infra.security.TokenDadosJwt;
import upx.facens.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosLogin dados){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dados.user(), dados.password());
        var auth = manager.authenticate(authToken);
        String tokenJwt = tokenService.gerarToken((Login) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDadosJwt(tokenJwt));
    }


}
