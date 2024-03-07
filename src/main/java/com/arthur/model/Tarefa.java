package com.arthur.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Tarefa {
    @Id
    private String id;

}
