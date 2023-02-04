package com.backendjava.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {


    private static final long serialVersionUID = -936888274250804829L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String surname;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_role"})})
    private List<Role> roles;

    @JsonIgnoreProperties(value = "user")
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "user")
    private List<Publication> publications;

    {
        this.roles = new ArrayList<>();
    }
}
