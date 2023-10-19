package upx.facens.domain.problema;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemaRepository extends JpaRepository<Problema,Long> {
    Page<Problema> findAllByInativoFalse(Pageable pagina);
}
