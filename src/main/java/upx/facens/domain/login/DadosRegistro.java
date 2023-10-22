package upx.facens.domain.login;

import jakarta.validation.constraints.NotBlank;

public record DadosRegistro(
        @NotBlank
        String user,
        @NotBlank
        String password) {

}
