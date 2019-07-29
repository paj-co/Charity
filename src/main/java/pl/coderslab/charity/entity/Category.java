package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public @Data class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Donation> donations;

}
