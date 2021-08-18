package com.core.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculaSerie")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idpeliserie;
    @Column(nullable = false)
    private String titulo;
    private String img;
    @Size(min = 1 , max =  5)
    private int calificacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    
    
    
    @ManyToMany(mappedBy = "peliculas",cascade = CascadeType.ALL)
    private List<Personaje> personajes = new ArrayList<Personaje>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Genero> genero;

    public PeliculaSerie() {
    }

    public PeliculaSerie(String titulo, String img, int calificacion, Date fechaCreacion, List<Genero> genero) {
        this.titulo = titulo;
        this.img = img;
        this.calificacion = calificacion;
        this.fechaCreacion = fechaCreacion;
        this.genero = genero;
    }

   
    
}
