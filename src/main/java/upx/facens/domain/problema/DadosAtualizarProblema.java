package upx.facens.domain.problema;

public record DadosAtualizarProblema(
        Long id,
        String cep,
        TipoProblema tipoProblema,
        String foto,
        String descricao,
        boolean inativo
) {
}
