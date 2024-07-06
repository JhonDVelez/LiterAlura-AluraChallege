package com.jdvelez.LiterAlura_AluraChallenge.repository;

import com.jdvelez.LiterAlura_AluraChallenge.model.Lenguaje;
import com.jdvelez.LiterAlura_AluraChallenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    // Busca un determinado libro basado en su nombre
    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    Libro encontrarPorNombre(String titulo);

    // Busca los libros que contengan un lenguaje determinado
    @Query("SELECT l FROM Libro l WHERE array_contains(l.lenguaje, :idioma)")
    List<Libro> encontrarPorIdioma(Lenguaje idioma);
}
