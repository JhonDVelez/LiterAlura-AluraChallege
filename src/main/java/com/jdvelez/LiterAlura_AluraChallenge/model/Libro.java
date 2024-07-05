package com.jdvelez.LiterAlura_AluraChallenge.model;

import java.util.List;

public class Libro {
    private Integer libroId;
    private String titulo;
    private List<String> lenguaje;
    private Integer numDescargas;


    public Libro(DatosLibro datos) {
        this.libroId = datos.libroId();
        this.titulo = datos.titulo();
        this.lenguaje = datos.lenguaje();
        this.numDescargas = datos.numDescargas();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libroId=" + libroId +
                ", titulo='" + titulo + '\'' +
                ", lenguaje='" + lenguaje.stream() + '\'' +
                ", numDescargas=" + numDescargas +
                '}';
    }
}
