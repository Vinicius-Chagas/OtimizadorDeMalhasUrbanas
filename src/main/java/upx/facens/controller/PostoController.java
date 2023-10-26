package upx.facens.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import upx.facens.domain.posto.*;

@RestController
@RequestMapping("/posto")
public class PostoController {

    @Autowired
    private PostoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosPosto dados, UriComponentsBuilder uriBuilder){
        Posto item = new Posto(dados);
        repository.save(item);
        var uri = uriBuilder.path("/problema/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPosto(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPosto> detalhes(@PathVariable Long id){
        Posto item = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPosto(item));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPosto>> listar(Pageable pageable){
        Page<DadosDetalhamentoPosto> page = repository.findAll(pageable).map(DadosDetalhamentoPosto::new);

        return ResponseEntity.ok(page);
    }
}
