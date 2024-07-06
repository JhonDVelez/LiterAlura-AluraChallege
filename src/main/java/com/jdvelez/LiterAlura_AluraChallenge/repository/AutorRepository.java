package com.jdvelez.LiterAlura_AluraChallenge.repository;

import com.jdvelez.LiterAlura_AluraChallenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombre%")
    Autor encontrarPorNombre(String nombre);
}
