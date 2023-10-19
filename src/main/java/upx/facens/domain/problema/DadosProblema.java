package upx.facens.domain.problema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosProblema(
        @NotBlank
        String cep,
        @NotNull
        TipoProblema tipoProblema,
        @NotBlank
        String foto,
        @NotBlank
        String descricao

) {
}
