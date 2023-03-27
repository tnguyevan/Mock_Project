package com.vti.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Catalog")
public class Catalog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "catalogId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`", nullable = false, length = 30)
    private String name;

    @Column(name = "image", nullable = false, length = 100)
    private String image;

    @OneToMany(mappedBy = "catalog")
    private List<Product> products;

    public Catalog(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}