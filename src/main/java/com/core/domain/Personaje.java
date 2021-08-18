package com.core.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personaje")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idpersonaje;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private int edad;
    private float peso;
    private String historia;
    private String img;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "personaje_pelicula",joinColumns = @JoinColumn(name = "fk_pelicula"),inverseJoinColumns = @JoinColumn(name = "fk_personaje"))
    private List<PeliculaSerie> peliculas;
    
    public Personaje() {
    }

    public Personaje(String nombre, int edad, float peso, String historia, String img, List<PeliculaSerie> peliculas) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.img = img;
        this.peliculas = peliculas;
    }

    

}
