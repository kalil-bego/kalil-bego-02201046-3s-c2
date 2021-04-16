package com.kalil.kalil.repositiorio;

import com.kalil.kalil.dominio.Golpe;
import com.kalil.kalil.dominio.Lutador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LutadorRepository extends JpaRepository<Lutador, Integer> {
    List<Lutador> findByLutadorOrderByforcaGolpeAsc();
    List<Lutador> findByVivoTrue();
    List<Lutador> findByVivoFalse();
    List<Golpe> findByidLutadorBateLikeAndidLutadorApanha(Integer idBate, Integer idApanha);
}
