package upx.facens.domain.posto;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarPosto(
        Long id,

        String nome,

        String local,
        double gasolina,
        double alcool,
        boolean inativo
) {
}
