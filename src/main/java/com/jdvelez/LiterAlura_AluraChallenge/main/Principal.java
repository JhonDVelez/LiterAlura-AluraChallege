package com.jdvelez.LiterAlura_AluraChallenge.main;

import com.jdvelez.LiterAlura_AluraChallenge.model.*;
import com.jdvelez.LiterAlura_AluraChallenge.repository.AutorRepository;
import com.jdvelez.LiterAlura_AluraChallenge.repository.LibroRepository;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConsumoAPI;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Principal {
    private final Scanner leer = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?search=";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepos;
    private final AutorRepository autorRepos;

    public Principal(LibroRepository libroRepos, AutorRepository autorRepos) {
        this.libroRepos = libroRepos;
        this.autorRepos = autorRepos;
    }


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
                            .map(l -> Lenguaje.fromString(l).getLengEspanol()).collect(Collectors.joining(" - ")) +
                    "\nDescargas Totales: " + libroObtenido.get().numDescargas() +
                    "\n--------------------------------------------------\n");

            Libro datosLibro = new Libro(libroObtenido);
            if(libroRepos.encontrarPorNombre(datosLibro.getTitulo()) == null) {
                List<Autor> autorLibro = libroObtenido.get().autores().stream().map(Autor::new).toList();
                List<Autor> autores = new ArrayList<>();
                for (Autor autor : autorLibro) {
                    Autor autorExistente = autorRepos.encontrarPorNombre(autor.getNombre());
                    if (autorExistente != null) {
                        autores.add(autorExistente);
                    } else {
                        autores.add(autor);
                        autorRepos.save((autor));
                    }
                }
                datosLibro.setAutores(autores);
                libroRepos.save(datosLibro);
            }else{
                System.out.println("El libro ya esta almacenado");
            }
        }else {
            System.out.println("Libro no encontrado");
        }
    }
}
