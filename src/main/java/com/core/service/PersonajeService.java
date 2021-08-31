package com.core.service;

import com.core.domain.Personaje;
import com.core.exceptions.*;
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
    public void deletePersonaje(Long id) {
        if(personajeRepo.existsById(id)){
               personajeRepo.deleteById(id);
        }else{
            throw new NotFoundException("Id Pj not found");
        }
    }

    @Transactional
    public Personaje editPersonaje(Long id, Personaje personaje) {
        if (personajeRepo.existsById(id)) {
            personaje.setIdpersonaje(id);
            return personajeRepo.save(personaje);
        }else{
            throw new NotFoundException("Pj not found");
        }
    }

    @Transactional
    public Personaje addPersonaje(Personaje personaje) {
       if(!personajeRepo.existsByNombre(personaje.getNombre())){
           return personajeRepo.save(personaje);
       }else{
           throw new BadRequestException("Pj already exists");
       }
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> findByEdad(int edad) {
        if(personajeRepo.existsByEdad(edad)){
            return personajeRepo.findByEdad(edad);
        }else{
            throw new NotFoundException("pj Not Found By Age");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> findByName(String nombre) {
        if(personajeRepo.existsByNombre(nombre)){
            return personajeRepo.findByNombre(nombre);
        }else{
            throw new NotFoundException("pj Not Found By Name");
        }
    }

    @Transactional(readOnly = true)
    public Optional<Personaje> getByIdMovie(Long id) {
        if(peliSerieService.existsId(id)){
            return personajeRepo.findByPeliculas(peliSerieService.getById(id));
        }else{
            throw new NotFoundException("pj Not Found By IdMovie");
        }
    }

}
