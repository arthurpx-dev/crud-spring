package com.arthur.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.model.Course;
import com.arthur.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

import java.util.List;

@Valid // O controller vai validar todas
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

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id) { // Long é objeto
        return courseRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @Valid Course course) { // verifica se as informações do front são válidas
        // System.out.println(course.getName());
        return courseRepository.save(course); // Salvando informações através do
        // repositório

        // return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.getName());
                    recordFound.setCategory(course.getCategory());
                    Course updated = courseRepository.save(recordFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // Remoção física
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
