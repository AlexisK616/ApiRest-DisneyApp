package com.core.controller;

import com.core.domain.PeliculaSerie;
import com.core.service.PeliculaSerieService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MovieController {

    PeliculaSerieService peliculaService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<PeliculaSerie> getAll() {
        return peliculaService.getAll();
    }

    @PostMapping("/movies/add")
    public ResponseEntity<?> add(@RequestBody PeliculaSerie peliserie) {
        Map<String, Object> response = new HashMap();
        try {
            peliculaService.add(peliserie);
            response.put("mensaje", "Pelicula agregada existosamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap();
        try {
            peliculaService.delete(id);
            response.put("mensaje", "Pelicula eliminada existosamente");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("mensaje", "error al eliminar el personaje");
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/movies/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody PeliculaSerie peliserie) {
        Map<String, Object> response = new HashMap();
        try {
            PeliculaSerie mov = peliculaService.edit(id, peliserie);
            if (mov != null) {
                response.put("mensaje", "Pelicula editada existosamente");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
            } else {
                response.put("error", "Pelicula no encotrada");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/movies", params = "name")
    public ResponseEntity<?> findByName(@RequestParam(name = "name") String name) {
        Map<String, Object> response = new HashMap();
        Optional<PeliculaSerie> mov = peliculaService.getByTitulo(name);
        if (mov != null) {
            response.put("response", mov);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/movies", params = "genre")
    public ResponseEntity<?> findByGenre(@RequestParam(name = "genre") Long id) {
        Map<String, Object> response = new HashMap();
        Optional<PeliculaSerie> mov = peliculaService.getByGenero(id);
        if (mov != null) {
            response.put("response", mov);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/movies", params = "order")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<PeliculaSerie> findAllByOrder(@RequestParam(name = "order") String order) {
        return peliculaService.getAllBySort(order);
    }
}
