package com.jdvelez.LiterAlura_AluraChallenge.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos (
        @JsonAlias("count")Integer conteo,
        @JsonAlias("results") List<DatosLibro> libros
){
}
