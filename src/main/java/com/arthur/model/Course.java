package com.arthur.model;

import com.arthur.enums.Category;
import com.arthur.enums.Status;
import com.arthur.enums.converters.CategoryConverter;
import com.arthur.enums.converters.StatusConverter;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// @Getter
// @Setter
@Data // Faz a mesma função que getter e setter
@Entity // entidade do jpa , que vai fazer mapeamento com banco de dados
// @Table(name = "cursos") Se ja tiver a tabela criada, se o nome da tabela for
// o da entidade , não
// precisa
@SQLDelete(sql = "Update Course Set status = 'Inativo' WHERE id = ? ")
@SQLRestriction("status = 'Ativo'") // @where(clause = "status ='Ativo")

public class Course { // Vai criar a tabela com esse nome
    @Id // chave primária
    @GeneratedValue(strategy = GenerationType.AUTO) // Valor seja gerado automaticamente pelo banco de dados quando
                                                    // inserido registro
    @JsonProperty("_id") // Passar qual nome quiser utilizar
    // @JsonIgnore Ignorar tais dados
    private Long id;

    @NotBlank // Caractere sem ser espaço
    @NotNull // Validação , não deixa ser null e nem vazio
    @Length(min = 3, max = 100)
    // @Column(name = "nome") // fazer associação se tiver nomes diferentes nas
    // colunas, Colocar informações ,
    // gera maior quantidade possível no banco de dados , e
    // por isso tem que especificar
    @Column(length = 200, nullable = false) // Quando obrigatorio informar valor use false
    private String name;

    @NotNull

    @Column(length = 10, nullable = false) // não usar muito espaço , e especificar
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;
}
