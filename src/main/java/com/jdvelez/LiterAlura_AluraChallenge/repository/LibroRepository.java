package com.jdvelez.LiterAlura_AluraChallenge.repository;

import com.jdvelez.LiterAlura_AluraChallenge.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l WHERE l.titulo ILIKE %:titulo%")
    Libro encontrarPorNombre(String titulo);
}
