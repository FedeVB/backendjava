package com.backendjava.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @JsonIgnore
//    @JsonIgnoreProperties(value = "publications")
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public Publication(Integer id, String tittle, String content, LocalDate date) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.date = date;
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }
}
