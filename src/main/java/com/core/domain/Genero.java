package com.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name="genero")
public class Genero implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idGenero;
    
    private String nombre;
    private String img;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_pelicula")
    PeliculaSerie pelicula;
    
    public Genero() {
    }
    
    
    public Genero(String nombre, String img) {
        this.nombre = nombre;
        this.img = img;
    }
    
    
    
}
