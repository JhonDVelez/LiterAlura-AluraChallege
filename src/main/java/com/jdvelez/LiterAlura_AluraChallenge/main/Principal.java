package com.jdvelez.LiterAlura_AluraChallenge.main;

import com.jdvelez.LiterAlura_AluraChallenge.model.*;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConsumoAPI;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leer = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void Menu(){
        var eleccion = -1;
        while(eleccion != 0){
            var menu = """
                    --------------------------------------------------
                    Elija la opción a través de su número:
                    1 - Buscar libro por titulo
                    2 - Listar libros Registrados
                    3 - Listar autores registrados
                    4 - listar autores vivos en un determinado año
                    5 - listar libros por idioma
                    
                    0 - Salir
                    --------------------------------------------------
                    """;
            System.out.print(menu);
            try{
                eleccion = Integer.parseInt(leer.nextLine());

                switch (eleccion){
                    case 0:
                        break;
                    case 1:
                        getDatosLibro();
                        break;
                    default:
                        System.out.println("Elija una opción valida por favor.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Caracteres no validos por favor ingrese un numero");
            }
        }

    }

    private void getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var libro = leer.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + libro.replace(" ","%20"));
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroObtenido = datos.libros().stream().findFirst();
        if(libroObtenido.isPresent()){
            System.out.println("\n----------------------Libro-----------------------" +
                    "\nTitulo: " + libroObtenido.get().titulo() +
                    "\nAutores: " + libroObtenido.get().autores().stream()
                            .map(DatosAutor::nombreAutor).collect(Collectors.joining(" - ")) +
                    "\nLenguajes: " + libroObtenido.get().lenguaje().stream()
                            .map(e -> Lenguaje.fromString(e).name()).collect(Collectors.joining(" - ")) +
                    "\nDescargas Totales: " + libroObtenido.get().numDescargas() +
                    "\n--------------------------------------------------\n");
        }else {
            System.out.println("Libro no encontrado");
        }
    }


}
