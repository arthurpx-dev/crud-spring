package com.arthur.controller;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.dto.CourseDTO;
import com.arthur.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Valid // O controller vai validar todas
@RestController // Fala que contem endpoint , contendo post, get etc ...
@RequestMapping("/api/courses") // Qual é o endpoint exposto,
public class CourseController {

    // injeção de dependência
    // @Autowired

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    // public CourseController(CourseRepository courseRepository) { Ou usar pelo
    // lombok
    // this.courseRepository = courseRepository;
    // }

    // @RequestMapping(method = RequestMethod.GET) Mesma coisa que GetMapping
    @GetMapping
    public List<CourseDTO> list() {
        return courseService.list(); // metodos do repositorio jpa, se tiver milhares de dados , usar paginação
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id) { // Long é objeto
        return courseService.findById(id);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) { // verifica se as informações do front são
        // válidas

        return courseService.create(course); // Salvando informações através do
        // repositório

        // return ResponseEntity.status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.update(id, course);

    }

    @DeleteMapping("/{id}") // Remoção Lógica
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive @NonNull Long id) {
        courseService.delete(id);

    }
}
