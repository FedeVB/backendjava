package com.backendjava.app.models.entity;

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
    private String key;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_role"})})
    private List<Role> roles;

    {
        this.roles = new ArrayList<>();
    }
}