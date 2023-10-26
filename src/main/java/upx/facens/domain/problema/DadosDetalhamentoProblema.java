package upx.facens.domain.problema;

import upx.facens.domain.posto.Posto;

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
