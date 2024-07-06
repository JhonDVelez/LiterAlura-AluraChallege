package com.jdvelez.LiterAlura_AluraChallenge.main;

import com.jdvelez.LiterAlura_AluraChallenge.model.*;
import com.jdvelez.LiterAlura_AluraChallenge.repository.AutorRepository;
import com.jdvelez.LiterAlura_AluraChallenge.repository.LibroRepository;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConsumoAPI;
import com.jdvelez.LiterAlura_AluraChallenge.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {
    private final Scanner leer = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "http://gutendex.com/books/?search=";
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepos;
    private final AutorRepository autorRepos;
    private List<Libro> libros;
    private List<Autor> autores;

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
                    2 - Listar libros registrados
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
                    case 2:
                        librosAlmacenados();
                        break;
                    case 3:
                        autoresAlmacenados();
                        break;
                    case 4:
                        autoresVivos();
                        break;
                    case 5:
                        librosPorIdioma();
                        break;
                    default:
                        System.out.println("Elija una opción valida por favor.");
                }
            }catch (NumberFormatException e) {
                System.out.println("Caracteres no validos por favor ingrese un numero");
            }
        }

    }

    // Obtiene los datos de la API y los almacena en la base de datos
    private void getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var libro = leer.nextLine();
        // Realiza la solicitud a la API
        var json = consumoApi.obtenerDatos(URL_BASE + libro.replace(" ","%20"));
        //System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        // Toma el primero de los resultados
        Optional<DatosLibro> libroObtenido = datos.libros().stream().findFirst();
        if(libroObtenido.isPresent()){
            Libro datosLibro = new Libro(libroObtenido);
            // Verifica si el libro ya está o no almacenado
            if(libroRepos.encontrarPorNombre(datosLibro.getTitulo()) == null) {
                // Si no está almacenado realiza la lista de los autores verificando si ya están almacenados
                List<Autor> autorLibro = libroObtenido.get().autores().stream().map(Autor::new).toList();
                autores.clear();
                verificaAutorAlmacenado(autorLibro);
                // Añade los autores al libro y almacena el libro
                datosLibro.setAutores(autores);
                libroRepos.save(datosLibro);
                System.out.println(datosLibro);
            }else{
                System.out.println("El libro ya esta almacenado");
            }
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    // A partir de una lista de autores realiza la verificacion de si estan o no almacenados en la BD
    // En caso de que no se procede a almacenarlos
    private void verificaAutorAlmacenado(List<Autor> autorLibro){
        for (Autor autor : autorLibro) {
            Autor autorExistente = autorRepos.encontrarPorNombre(autor.getNombre());
            if (autorExistente != null) {
                autores.add(autorExistente);
            } else {
                // Si el autor no está almacenado
                autores.add(autor);
                autorRepos.save((autor));
            }
        }
    }

    // Retorna de la BD todos los libros que se han almacenado y se imprimen en pantalla
    public void librosAlmacenados(){
        libros = libroRepos.findAll();
        libros.forEach(System.out::println);
    }

    // Retorna de la BD todos los autores almacenados y los imprime en pantalla
    public void autoresAlmacenados(){
        autores = autorRepos.findAll();
        autores.forEach(System.out::println);
    }

    // Genera una solicitud de los autores vivos en un año determinado
    public void autoresVivos(){
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar");
        try {
            Integer fecha = Integer.parseInt(leer.nextLine());
            autores = autorRepos.encontrarAutoresVivos(fecha);
            autores.forEach(System.out::println);
        }catch (NumberFormatException e){
            System.out.println("Caracteres no validos por favor ingrese un numero");
        }
    }

    // Genera una soliciud donde se obtienen los libros que contienen un lenguaje específico
    // También retorna aquellos que poseen mas de un lenguaje y contienen el solicitado
    public void librosPorIdioma(){
        System.out.println("Ingrese el idioma para buscar los libros");
        System.out.println("Por ejemplo: ingles o español. \nNo es necesario que incluya los acentos.");
        try {
            Lenguaje idioma = Lenguaje.fromEspanol(leer.nextLine());
            libros = libroRepos.encontrarPorIdioma(idioma);
            libros.forEach(System.out::println);
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
