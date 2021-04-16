package repositiorio;

import dominio.Golpe;
import dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {
    List<Lutador> findByLutadorOrderByforcaGolpeAsc();
    List<Lutador> findByconcentracoesRealizadas();
    List<Lutador> findByVivoTrue();
    List<Lutador> findByVivoFalse();
    List<Golpe> ;
}
