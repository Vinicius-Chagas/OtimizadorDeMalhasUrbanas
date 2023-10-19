package upx.facens.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import upx.facens.domain.login.Login;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;


    public String gerarToken(Login usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret); // Tipo de criptografia e senha de criptografia
            return JWT.create()
                    .withIssuer("API upx.Facens")
                    .withSubject(usuario.getUsername()) // Vincular o usuário
                    .withExpiresAt(dataExpiracao()) // Data de expiração para o token
                    .sign(algoritmo); // Algoritmo de criptografia
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }

    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String tokenJwt){// Verifica a validade do token
        DecodedJWT decodedJWT;
        try{
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API upx.Facens")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado");
        }
    }
}
