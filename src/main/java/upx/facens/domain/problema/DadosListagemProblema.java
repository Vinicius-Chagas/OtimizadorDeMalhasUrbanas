package upx.facens.domain.problema;

public record DadosListagemProblema(
        String cep,
        TipoProblema tipoProblema,
        String foto,
        String descricao
) {
    public DadosListagemProblema(Problema item) {
        this(item.getCep(),item.getTipoProblema(),item.getFoto(), item.getDescricao());
    }
}