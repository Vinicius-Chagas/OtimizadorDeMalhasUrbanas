package upx.facens.domain.posto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "posto")
@Table(name = "postos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Posto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;
    private double gasolina;
    private double alcool;
    private boolean inativo;

    public Posto(DadosPosto dados) {
        this.nome = dados.nome();
        this.local = dados.local();
        this.gasolina = dados.gasolina();
        this.alcool = dados.alcool();
    }
}
