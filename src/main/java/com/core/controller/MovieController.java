package com.core.controller;

import com.core.domain.PeliculaSerie;
import com.core.service.PeliculaSerieService;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.add(peliserie));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(peliculaService.delete(id));
    }

    @PutMapping("/movies/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody PeliculaSerie peliserie) {
      return ResponseEntity.status(HttpStatus.OK).body(peliculaService.edit(id, peliserie));
    }

    @RequestMapping(value = "/movies", params = "name")
    public ResponseEntity<?> findByName(@RequestParam(name = "name") String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(peliculaService.getByTitulo(name));
    }

    @RequestMapping(value = "/movies", params = "genre")
    public ResponseEntity<?> findByGenre(@RequestParam(name = "genre") Long id) {
      return ResponseEntity.status(HttpStatus.FOUND).body(peliculaService.getByGenero(id));
    }

    @RequestMapping(value = "/movies", params = "order")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<PeliculaSerie> findAllByOrder(@RequestParam(name = "order") String order) {
        return peliculaService.getAllBySort(order);
    }
}
