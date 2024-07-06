package com.jdvelez.LiterAlura_AluraChallenge;

import com.jdvelez.LiterAlura_AluraChallenge.main.Principal;
import com.jdvelez.LiterAlura_AluraChallenge.repository.AutorRepository;
import com.jdvelez.LiterAlura_AluraChallenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository libroRepos;
	@Autowired
	private AutorRepository autorRepos;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepos,autorRepos);
		principal.Menu();
	}
}
