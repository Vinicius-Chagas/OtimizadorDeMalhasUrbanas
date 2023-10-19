package upx.facens.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import upx.facens.domain.login.Login;
import upx.facens.domain.login.LoginRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { //Filter do Spring boot

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LoginRepository repository;

    @Override //Filtro chamado uma vez por requisição
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJwt = recuperarToken(request);
        if(tokenJwt != null) {
            String subject = tokenService.getSubject(tokenJwt);
            UserDetails usuario = repository.findByUser(subject);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //Pegar o token do cabeçalho, buscar o usuario no banco de dados e em seguida, forçar a authentication do usuario antes de continuar.
        }
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}