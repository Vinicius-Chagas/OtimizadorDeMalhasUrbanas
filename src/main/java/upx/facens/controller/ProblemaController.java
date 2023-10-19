package upx.facens.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import upx.facens.domain.problema.*;

@RestController
@RequestMapping("/problema")
public class ProblemaController {

    @Autowired
    private ProblemaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosProblema dados, UriComponentsBuilder uriBuilder){
            Problema item = new Problema(dados);
            repository.save(item);
            var uri = uriBuilder.path("/problema/{id}").buildAndExpand(item.getId()).toUri();

            return ResponseEntity.created(uri).body(new DadosDetalhamentoProblema(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoProblema> detalhes(@PathVariable Long id)
    {
        Problema item = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProblema(item));
    }

    @GetMapping
    public ResponseEntity listar(Pageable pagina){
        Page<DadosListagemProblema> page = repository.findAllByInativoFalse(pagina).map(DadosListagemProblema::new);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoProblema> delete(@PathVariable Long id){
        Problema item = repository.getReferenceById(id);
        item.inativo();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarProblema dados){
        Problema item = repository.getReferenceById(dados.id());
        item.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProblema(item));
    }
}
