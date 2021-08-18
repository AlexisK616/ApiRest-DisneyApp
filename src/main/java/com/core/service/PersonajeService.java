package com.core.service;

import com.core.domain.Personaje;
import com.core.repository.IPersonajeRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PersonajeService {

    IPersonajeRepository personajeRepo;
    PeliculaSerieService peliSerieService;

    @Transactional(readOnly = true)
    public ArrayList<Personaje> getPersonajes() {
        return (ArrayList<Personaje>) personajeRepo.findAll();
    }

    @Transactional
    public String deletePersonaje(Long id) {
        personajeRepo.deleteById(id);
        return "Personaje Eliminado Sastifactoriamente";
    }

    @Transactional
    public Personaje editPersonaje(Long id, Personaje personaje) {
        if (personajeRepo.existsById(id)) {
            personaje.setIdpersonaje(id);
            return personajeRepo.save(personaje);
        }
        return null;
    }

    @Transactional
    public Personaje addPersonaje(Personaje personaje) {
        return personajeRepo.save(personaje);
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> findByEdad(int edad) {
        return personajeRepo.findByEdad(edad);
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> findByName(String nombre) {
        return personajeRepo.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> getByIdMovie(Long id) {
        return personajeRepo.findByPeliculas(peliSerieService.getById(id));
    }

}
