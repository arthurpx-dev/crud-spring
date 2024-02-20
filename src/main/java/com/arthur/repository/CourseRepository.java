package com.arthur.repository;
// Programação orientada a anotação 

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.arthur.model.Course;

@Repository // Long identificador, implica em diferentes tipos de dados sendo usados como
            // chave primária em suas respectivas entidades no banco de dados.

public interface CourseRepository extends JpaRepository<Course, Long> { // consegue extender as interfaces do spring
                                                                        // data

}
