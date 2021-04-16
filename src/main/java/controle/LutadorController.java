package controle;

import dominio.Golpe;
import dominio.Lutador;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositiorio.LutadorRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lutadores")
public class LutadorController {

    @Autowired
    private LutadorRepository repository;

    // a
    @PostMapping
    public ResponseEntity postLutador(@RequestBody @Valid Lutador lutador) {
        repository.save(lutador);
        return ResponseEntity.status(201).build();
    }

    // b
    @GetMapping
    public ResponseEntity getLutadores() {
        return ResponseEntity.status(200).body(repository.findByLutadorOrderByforcaGolpeAsc());
    }

    // c
    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivos() {
        return ResponseEntity.status(200).body(repository.findByVivoTrue().stream().count());
    }

    // d
    @PostMapping("/{idLutador}/concentrar")
    public ResponseEntity postConcentrar(@PathVariable Integer idLutador) {
        Optional<Lutador> lutador = repository.findById(idLutador);
        if (repository.existsById(idLutador)) {
            if (lutador.get().getConcentracoesRealizadas() <= 3) {
                lutador.get().setVida(lutador.get().getVida() * 1.15);
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.status(400).body("Lutador ja se concentrou 3 vezes!");
        }
        return ResponseEntity.status(204).build();
    }

    // e
    @PostMapping("/golpe")
    public ResponseEntity postGolpe(@RequestBody Golpe golpe) {
        int apanha = golpe.getIdLutadorApanha();
        int bate = golpe.getIdLutadorBate();

        Optional<Lutador> lutadorApanha = repository.findById(apanha);
        Optional<Lutador> lutadorBate = repository.findById(bate);

        List<Lutador> luta = new ArrayList<>(lutadorApanha, lutadorBate);

        lutadorApanha.get().setVida(lutadorBate.get().getForcaGolpe());
        return ResponseEntity.status(201).body(luta);
    }

    // f
    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.status(200).body(repository.findByVivoFalse());
    }
}
