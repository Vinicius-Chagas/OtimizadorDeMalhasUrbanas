package upx.facens.domain.posto;

import jakarta.validation.constraints.NotBlank;

public record DadosListagemPosto(

        String nome,
        String local,
        double gasolina,
        double alcool,
        boolean inativo
) {
}
