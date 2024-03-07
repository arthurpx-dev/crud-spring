package com.arthur.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.arthur.dto.CourseDTO;
import com.arthur.dto.mapper.CourseMapper;

import com.arthur.exception.RecordNotFoundException;
import com.arthur.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Valid
@Service /// Vai ter lógica de negócio
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO) // Lambda
                .collect(Collectors.toList());
    }

    public CourseDTO findById(@NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO) // Não precisa stream pq apenas um
                .orElseThrow(() -> new RecordNotFoundException(id));

    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));

    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(course.name());
                    recordFound.setCategory(this.courseMapper.convertCategoryValue(course.category()));
                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id)));

    }

    // @SuppressWarnings("null")
    // public void delete(@NotNull @Positive Long id) {
    // courseRepository.delete(courseRepository.findById(id)
    // .orElseThrow(() -> new RecordNotFoundException(id)));

    // }
}

// Service vai pegar o DTO , transformar em entidade , fazer os acessos ao banco
// de dados e retornar as respostas para o controller
// Controller classe responsável por obter informações e passar para o time
// responsável
// Service fazer o acesso ao repository que é a interface com o banco
// DTO empacotar tudo para ser enviado de volta
// Recebido por pessoa que anota o pedido , recebe dinheiro ou cartão, detalhe
// do pedido feito na tela.
// Outra tela recebido para time que monta o lanche e verifica detalhes , monta
// e embrulha.
// Outra pessoa com um acesso e olha na tela qual determinado pedito , vai
// juntar tudo e entrega o pedido.