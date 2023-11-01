package ru.workwear.workwearshop.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor(force = true)
public class Product implements Serializable {

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
    @Column(name = "coast")
    private BigDecimal coast;
    @Column(name = "placed_at")
    private Date placedAt;
    @Column(name = "image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH)
    private List<Size> sizes;

    @PrePersist
    private void placedAt(){
        this.placedAt = new Date();
    }
}
