package com.backendjava.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "publications")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publication implements Serializable {

    private static final long serialVersionUID = 1116001306289683980L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    private String tittle;
    @NotBlank
    private String content;

    @JsonIgnoreProperties(value = "publications")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
}
