package ru.workwear.workwearshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor(force = true)
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "internal")
    private boolean internal;
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    private List<Product> products;
}
