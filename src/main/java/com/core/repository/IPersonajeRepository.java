package com.core.repository;
import com.core.domain.PeliculaSerie;
import com.core.domain.Personaje;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje,Long>{
    Optional<Personaje> findByNombre(String nombre);
    Optional<Personaje> findByEdad(int edad);
    Optional<Personaje> findByPeliculas(Optional<PeliculaSerie> peliserie);
    boolean existsByNombre(String nombre);
    boolean existsByEdad(int edad);
    boolean existsByPeliculas(Optional<PeliculaSerie> peliserie);
    
}
