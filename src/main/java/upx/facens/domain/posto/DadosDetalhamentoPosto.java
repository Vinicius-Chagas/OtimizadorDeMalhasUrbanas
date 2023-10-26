package upx.facens.domain.posto;

public record DadosDetalhamentoPosto(
        Long id,
        String nome,
        String local,
        double gasolina,
        double alcool,
        boolean inativo
) {
    public DadosDetalhamentoPosto(Posto item) {
        this(item.getId(), item.getNome(), item.getLocal(), item.getGasolina(),item.getAlcool(), item.isInativo());
    }
}
