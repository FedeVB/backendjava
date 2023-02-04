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
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 9027316424141017741L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    {
        this.users = new ArrayList<>();
    }
}
