package com.core.controller;

import com.core.domain.*;
import com.core.service.PersonajeService;
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
public class CharacterController {

    PersonajeService pjService;

    @GetMapping("/characters")
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Personaje> getAll() {
        return pjService.getPersonajes();
    }

    @PostMapping("/characters/add")
    public ResponseEntity<?> addPj(@RequestBody Personaje pj) {
        Map<String, Object> response = new HashMap();
        try {
            pjService.addPersonaje(pj);
            response.put("mensaje", "se agrego existosamente");
        } catch (DataAccessException e) {
            response.put("mensaje", "error al agregar el personaje");
            response.put("error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/characters/edit/{id}")
    public ResponseEntity<?> editPj(@PathVariable("id") Long id, @RequestBody Personaje personaje) {
        Map<String, Object> response = new HashMap();
        try {
            Personaje pj = pjService.editPersonaje(id, personaje);
            if (pj != null) {
                response.put("mensaje", "se actualizo existosamente");
            } else {
                response.put("mensaje", "error al encontrar id de el personaje");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/characters/{id}")
    public ResponseEntity<?> deletePj(@PathVariable("id") Long id) {

        Map<String, Object> response = new HashMap();
        try {
            pjService.deletePersonaje(id);
            response.put("mensaje", "se elimino existosamente");
        } catch (DataAccessException e) {
            response.put("mensaje", "error al eliminar el personaje");
            response.put("error:", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/characters", params = "name")
    public ResponseEntity<?> getByName(@RequestParam(name = "name") String name) {
        Map<String, Object> response = new HashMap();
        Optional<Personaje> pj = pjService.findByName(name);
        if (pj != null) {
            response.put("response", pj);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/characters", params = "edad")
    public ResponseEntity<?> getByEdad(@RequestParam(name = "edad") int edad) {
        Map<String, Object> response = new HashMap();
        Optional<Personaje> pj = pjService.findByEdad(edad);
        if (pj != null) {
            response.put("response", pj);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/characters", params = "movie")
    public ResponseEntity<?> getByIdMovie(@RequestParam(name = "movie") Long id) {
        Map<String, Object> response = new HashMap();
        Optional<Personaje> pj = pjService.getByIdMovie(id);
        if (pj != null) {
            response.put("response", pj);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
}
