package controle;

import dominio.Lutador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositiorio.LutadorRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid Lutador lutador) {
        repository.save(lutador);
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity getLutadores() {
        return ResponseEntity.status(200).body(repository.findByLutadorOrderByforcaGolpeAsc());
    }

    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivos() {
        return ResponseEntity.status(200).body(repository.findByVivoTrue().stream().count());
    }

    @PostMapping("/{}/concentrar")
    public ResponseEntity postConcentrar(@PathVariable Integer idLutador) {
        if (repository.existsById(idLutador)) {
            if(repository.findByconcentracoesRealizadas()) {

            }
        }
    }

    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody) {

    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.status(200).body(repository.findByVivoFalse());
    }
}
