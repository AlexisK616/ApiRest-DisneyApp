package com.core.service;

import com.core.domain.PeliculaSerie;
import com.core.exceptions.*;
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
            throw new BadRequestException("Put A Sort");
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
        if(peliSerieRepo.existsById(id)){
          return peliSerieRepo.findById(id);
        }else{
            throw new NotFoundException("Id not Found");
        }
    }

    @Transactional(readOnly = true)
    public Optional<PeliculaSerie> getByTitulo(String titulo) {
        if(peliSerieRepo.existsByTitulo(titulo)){
           return peliSerieRepo.findByTitulo(titulo);  
        }else{
            throw new NotFoundException("Title Not Found");
        }
    }

    @Transactional(readOnly = true)
    public Optional<PeliculaSerie> getByGenero(Long id) {
        if(generoRepo.existsById(id)){
           return peliSerieRepo.findByGenero(generoRepo.findById(id));
        }else{
            throw new NotFoundException("Genre Not Found");
        }
    }

    @Transactional
    public PeliculaSerie add(PeliculaSerie peliserie){
        if(!peliSerieRepo.existsById(peliserie.getIdpeliserie())){
            return peliSerieRepo.save(peliserie); 
        }else{
            throw new BadRequestException("Id Movie Already Exists");
        }
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
        }else{
            throw new BadRequestException("Id not exists");
        }
    }
    
    @Transactional 
    public boolean existsId(Long id){
        return peliSerieRepo.existsById(id);
    }
}
