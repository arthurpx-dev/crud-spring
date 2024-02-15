package com.arthur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.model.Course;
import com.arthur.repository.CourseRepository;

import lombok.AllArgsConstructor;

import java.util.List;

@RestController // Fala que contem endpoint , contendo post, get etc ...
@RequestMapping("/api/courses") // Qual é o endpoint exposto,
@AllArgsConstructor
public class CourseController {

    // injeção de dependência
    // @Autowired
    private final CourseRepository courseRepository; // não vai modificar a instancia , o spring vai fazer a injeção
                                                     // (final)

    // public CourseController(CourseRepository courseRepository) { Ou usar pelo
    // lombok
    // this.courseRepository = courseRepository;
    // }

    // @RequestMapping(method = RequestMethod.GET) Mesma coisa que GetMapping
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll(); // metodos do repositorio jpa, se tiver milhares de dados , usar paginação

    }

}
