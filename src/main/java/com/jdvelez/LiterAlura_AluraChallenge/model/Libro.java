package com.jdvelez.LiterAlura_AluraChallenge.model;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer libroNum;
    private String titulo;
    private List<Lenguaje> lenguaje;
    private Integer numDescargas;

    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "id_libro", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_autor", referencedColumnName = "id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Autor> autores;

    public Libro(){}

    public Libro(Optional<DatosLibro> datos) {
        this.libroNum = datos.get().libroId();
        this.titulo = datos.get().titulo();
        // Intenta comparar el dato de lenguaje obtenido de la API con el enum Lenguaje
        try {
            this.lenguaje = datos.get().lenguaje().stream().map(Lenguaje::fromString).toList();
        }catch (IllegalArgumentException e){
            //En caso de que no lo encuentre muestra el mensaje de la excepción y lo guarda como DESCONOCIDO
            System.out.println(e.getMessage());
            this.lenguaje = Collections.singletonList(Lenguaje.DESCONOCIDO);
        }
        this.numDescargas = datos.get().numDescargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLibroNum() {
        return libroNum;
    }

    public void setLibroNum(Integer libroNum) {
        this.libroNum = libroNum;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Lenguaje> getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(List<Lenguaje> lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(Integer numDescargas) {
        this.numDescargas = numDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "\n----------------------Libro-----------------------" +
                "\nTitulo: " + this.titulo +
                "\nAutores: " + this.autores.stream()
                .map(Autor::getNombre).collect(Collectors.joining(" - ")) +
                "\nLenguajes: " + this.lenguaje.stream()
                .map(Lenguaje::getLengEspanol).collect(Collectors.joining(" - ")) +
                "\nDescargas Totales: " + this.numDescargas +
                "\n--------------------------------------------------\n";
    }
}
