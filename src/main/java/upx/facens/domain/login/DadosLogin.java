package upx.facens.domain.login;

import jakarta.validation.constraints.NotEmpty;

public record DadosLogin(
        @NotEmpty
        String user,
        @NotEmpty
        String password) {
}
