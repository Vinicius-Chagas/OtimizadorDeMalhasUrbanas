package upx.facens.domain.problema;

public record DadosDetalhamentoProblema(
        Long id,
        String cep,
        TipoProblema tipoProblema,
        String foto,
        String descricao,
        boolean inativo
) {
    public DadosDetalhamentoProblema(Problema item) {
        this(item.getId(),item.getCep(),item.getTipoProblema(),item.getFoto(), item.getDescricao(), item.isInativo());
    }
}
