package com.core.service;

import com.core.domain.PeliculaSerie;
import com.core.repository.IGeneroRepository;
import com.core.repository.IPeliculaSerieRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PeliculaSerieService {

    IPeliculaSerieRepository peliSerieRepo;
    IGeneroRepository generoRepo;

    @Transactional(readOnly = true)
    public ArrayList<PeliculaSerie> getAll() {
        return (ArrayList<PeliculaSerie>) peliSerieRepo.findAll();
    }

    @Transactional(readOnly = true)
    public ArrayList<PeliculaSerie> getAllBySort(String sortType) {
        if (null == sortType) {
            return null;
        } else {
            switch (sortType) {
                case "ASC":
                    return (ArrayList<PeliculaSerie>) peliSerieRepo.findAll(Sort.by("fechaCreacion").ascending());
                case "DESC":
                    return (ArrayList<PeliculaSerie>) peliSerieRepo.findAll(Sort.by("fechaCreacion").descending());
                default:
                    return null;
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<PeliculaSerie> getById(Long id) {
        return peliSerieRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<PeliculaSerie> getByTitulo(String titulo) {
        return peliSerieRepo.findByTitulo(titulo);
    }

    @Transactional(readOnly = true)
    public Optional<PeliculaSerie> getByGenero(Long id) {
        return peliSerieRepo.findByGenero(generoRepo.findById(id));
    }

    @Transactional
    public PeliculaSerie add(PeliculaSerie peliserie) {
        return peliSerieRepo.save(peliserie);
    }

    @Transactional
    public String delete(Long id) {
        peliSerieRepo.deleteById(id);
        return "Recurso eliminado";
    }

    @Transactional
    public PeliculaSerie edit(Long id, PeliculaSerie pelicula) {
        if (peliSerieRepo.existsById(id)) {
            pelicula.setIdpeliserie(id);
           return peliSerieRepo.save(pelicula);
        }
        return null;
    }
}
