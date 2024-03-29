package com.arthur;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arthur.enums.Category;
import com.arthur.model.Course;
import com.arthur.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	// criar bean para exemplo
	@Bean // gerencie o ciclo de vida
	CommandLineRunner initDatabase(CourseRepository courseRepository) { // assim que tiver funiconando, o spring executa
																		// a lógica
		return args -> { // função lambda
			courseRepository.deleteAll(); // deletar a base de dados assim que inicializar o projeto e ter novos dados

			Course c = new Course();
			c.setName("Angular");
			c.setCategory(Category.FRONT_END);
			courseRepository.save(c); // Salvar nova entidade

			Course c1 = new Course();
			c1.setName("Spring");
			c1.setCategory(Category.BACK_END);
			courseRepository.save(c1);
		};
	}
}
