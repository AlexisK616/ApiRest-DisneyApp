package com.core.repository;

import com.core.domain.Genero;
import com.core.domain.PeliculaSerie;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie,Long>{
    Optional<PeliculaSerie> findByTitulo(String titulo);
    Optional<PeliculaSerie> findByGenero(Optional<Genero> genero);
    boolean existsByTitulo(String titulo);
}
