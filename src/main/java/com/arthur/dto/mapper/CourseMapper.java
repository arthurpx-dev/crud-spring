package com.arthur.dto.mapper;

import org.springframework.stereotype.Component;

import com.arthur.dto.CourseDTO;
import com.arthur.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

}
