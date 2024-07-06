package com.jdvelez.LiterAlura_AluraChallenge.repository;

import com.jdvelez.LiterAlura_AluraChallenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    // Busca un determinado autor basado en su nombre
    @Query("SELECT a FROM Autor a WHERE a.nombre ILIKE %:nombre%")
    Autor encontrarPorNombre(String nombre);

    // Compara las fechas de nacimiento y fallecimiento con un año y determina si están/estaban vivos en este
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= %:fecha% AND a.fechaFallecimiento >= %:fecha%")
    List<Autor> encontrarAutoresVivos(Integer fecha);
}
