package upx.facens.domain.problema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "problema")
@Table(name = "problema")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Problema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    @Column(name = "tipoProblema")
    @Enumerated(EnumType.STRING)
    private TipoProblema tipoProblema;
    private String foto;
    private String descricao;
    private boolean inativo;


    public Problema(DadosProblema dados) {
        this.cep = dados.cep();
        this.tipoProblema = dados.tipoProblema();
        this.foto = dados.foto();
        this.descricao = dados.descricao();
    }

    public void inativo() {
        this.inativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarProblema dados) {
        if(dados.cep() != null){
            this.cep = dados.cep();
        }
        if(dados.tipoProblema() != null){
            this.tipoProblema = dados.tipoProblema();
        }
        if(dados.foto() != null){
            this.foto = dados.foto();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
    }
}
