package com.core.controller;

import com.core.domain.*;
import com.core.service.PersonajeService;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CharacterController {

    PersonajeService pjService;

    @GetMapping("/characters")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Personaje> getAll() {
        return pjService.getPersonajes();
    }

    @PostMapping("/characters/add")
    public ResponseEntity<?> addPj(@RequestBody Personaje pj) {
       return ResponseEntity.status(HttpStatus.CREATED).body(pjService.addPersonaje(pj));
    }

    @PutMapping(path = "/characters/edit/{id}")
    public ResponseEntity<?> editPj(@PathVariable("id") Long id, @RequestBody Personaje personaje) {
       return ResponseEntity.status(HttpStatus.OK).body(pjService.editPersonaje(id, personaje));
    }

    @DeleteMapping(path = "/characters/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePj(@PathVariable("id") Long id) {
        pjService.deletePersonaje(id);
    }

    @RequestMapping(value = "/characters", params = "name")
    public ResponseEntity<?> getByName(@RequestParam(name = "name") String name) {
     return ResponseEntity.status(HttpStatus.FOUND).body(pjService.findByName(name));
    }

    @RequestMapping(value = "/characters", params = "age")
    public ResponseEntity<?> getByEdad(@RequestParam(name = "age") int edad) {
         return ResponseEntity.status(HttpStatus.FOUND).body(pjService.findByEdad(edad));
    }

    @RequestMapping(value = "/characters", params = "movie")
    public ResponseEntity<?> getByIdMovie(@RequestParam(name = "movie") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(pjService.getByIdMovie(id));
    }
}
