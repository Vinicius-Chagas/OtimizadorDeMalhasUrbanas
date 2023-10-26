package upx.facens.domain.posto;

import jakarta.validation.constraints.NotBlank;

public record DadosPosto(
        @NotBlank
        String nome,
        @NotBlank
        String local,
        double gasolina,
        double alcool,
        boolean inativo
) {
}
